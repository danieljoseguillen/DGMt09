package com.tarea9.dgmt09e11.servicios;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.tarea9.dgmt09e11.Repositorios.UsuarioRepository;
import com.tarea9.dgmt09e11.domain.Empleado;
import com.tarea9.dgmt09e11.domain.Usuario;
import com.tarea9.dgmt09e11.dto.EmpleadoDTO;
import com.tarea9.dgmt09e11.dto.EmpleadoFormDTO;
import com.tarea9.dgmt09e11.modelos.Estado;

@Component
public class EmpleadoDtoConverter {
    @Autowired
    private UsuarioRepository uRep;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private eService servicio;

    public List<EmpleadoDTO> convertListaEmpleadoToDTO(List<Empleado> listaEmpleados) {
        List<EmpleadoDTO> listaDTO = new ArrayList<>();
        for (Empleado empleado : listaEmpleados) {
            listaDTO.add(this.convertEmpleadoToDto(empleado));
        }
        return listaDTO;
    }

    public EmpleadoDTO convertEmpleadoToDto(Empleado empleado) {
        return modelMapper.map(empleado, EmpleadoDTO.class);
    }

    public Empleado convertDtoToEmpleado(EmpleadoFormDTO eDto, Usuario user) {
        System.out.println("Usuario ID desde DTO: " + user);
        Empleado emp = new Empleado(null,
                eDto.getNombre(),
                eDto.getCorreo(),
                eDto.getSalario(),
                eDto.getEstado(),
                eDto.getGenero(), user);
        System.out.println(emp);
        return emp;
    }

    public Empleado convertDtoToEmpleado(EmpleadoFormDTO eDto, Long id) {
        Usuario user = servicio.getById(id).getCreador();
        Empleado empleado = convertDtoToEmpleado(eDto, user);
        empleado.setId(id);
        return empleado;
    }

    public Empleado convertDtoToEmpleado(EmpleadoFormDTO eDto, Estado estado) {
        eDto.setEstado(estado);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario user = uRep.findByNombre(authentication.getName())
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario"));
        Empleado empleado = convertDtoToEmpleado(eDto, user);
        return empleado;
    }

}