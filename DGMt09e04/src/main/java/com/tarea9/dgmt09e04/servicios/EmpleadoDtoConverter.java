package com.tarea9.dgmt09e04.servicios;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tarea9.dgmt09e04.domain.Empleado;
import com.tarea9.dgmt09e04.domain.EmpleadoDTO;
import com.tarea9.dgmt09e04.domain.EmpleadoFormDTO;
import com.tarea9.dgmt09e04.modelos.Estado;

@Component
public class EmpleadoDtoConverter {
    @Autowired
    private dService departamentoService;
    @Autowired
    private ModelMapper modelMapper;

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

    public Empleado convertDtoToEmpleado(EmpleadoFormDTO eDto) {
        return new Empleado(null,
                eDto.getNombre(),
                eDto.getCorreo(),
                eDto.getSalario(),
                eDto.getEstado(),
                eDto.getGenero(),
                departamentoService.getById(eDto.getDepartamentoId()));
    }

    public Empleado convertDtoToEmpleado(EmpleadoFormDTO eDto, Long id) {
        Empleado empleado = convertDtoToEmpleado(eDto);
        empleado.setId(id);
        return empleado;
    }
    public Empleado convertDtoToEmpleado(EmpleadoFormDTO eDto, Estado estado) {
        eDto.setEstado(estado);
        Empleado empleado = convertDtoToEmpleado(eDto);
        return empleado;
    }
    // public Empleado convertDtoToEmpleado(EmpleadoEditDTO eDto) {
    // return new Empleado(eDto.getId(),
    // eDto.getNombre(),
    // eDto.getCorreo(),
    // eDto.getSalario(),
    // eDto.getEstado(),
    // eDto.getGenero(),
    // departamentoService.getById(eDto.getDepartamentoId()));
    // }

    // public Empleado convertDtoToEmpleado(EmpleadoNuevoDTO eDto) {
    // return new Empleado(null,
    // eDto.getNombre(),
    // eDto.getCorreo(),
    // eDto.getSalario(),
    // Estado.ACTIVO,
    // eDto.getGenero(),
    // departamentoService.getById(eDto.getDepartamentoId()));
    // }

}