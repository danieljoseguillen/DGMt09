package com.tarea9.dgmt09e11.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tarea9.dgmt09e11.Repositorios.EmpleadoRepository;
import com.tarea9.dgmt09e11.Repositorios.UsuarioRepository;
import com.tarea9.dgmt09e11.domain.Empleado;
import com.tarea9.dgmt09e11.domain.Usuario;
import com.tarea9.dgmt09e11.excepciones.EmployeeNotFoundException;
import com.tarea9.dgmt09e11.excepciones.EmptyEmployeeListException;
import com.tarea9.dgmt09e11.excepciones.UserActionNotAllowedException;
import com.tarea9.dgmt09e11.modelos.Genero;
import com.tarea9.dgmt09e11.modelos.Rol;

@Service
public class eServiceImpl implements eService {

    @Autowired
    EmpleadoRepository repositorio;

    @Autowired
    UsuarioRepository uRep;

    public List<Empleado> listAll() {
        List<Empleado> lista = repositorio.findAll();
        if (lista.isEmpty()) {
            throw new EmptyEmployeeListException();
        }
        return lista;
    }

    public List<Empleado> listSorted(Genero genero, String val) {
        List<Empleado> lista;
        if (genero == null) {
            lista = repositorio.findByNombreContainingIgnoreCase(val);
        } else if (val.isEmpty() || val == null) {
            lista = repositorio.findByGenero(genero);
        } else {
            lista = repositorio.findByGeneroAndNombreContainingIgnoreCase(genero, val);
        }
        if (lista.isEmpty()) {
            throw new EmptyEmployeeListException();
        }
        return lista;
    }

    public Empleado getById(long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Empleado agregar(Empleado empleado) {
        try {
            return repositorio.save(empleado);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo agregar el empleado: " + e.getMessage());
        }
    }

    public Empleado modificar(Empleado empleado) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usr = uRep.findByNombre(authentication.getName())
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario"));
        if (usr.getRol().equals(Rol.USER)) {
            if (!empleado.getCreador().getId().equals(usr.getId())) {
                throw new UserActionNotAllowedException();
            }
        }
        try {
            return repositorio.save(empleado);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo modificar el empleado. " + e.getMessage());
        }
    }

    public boolean borrarPorId(long id) {
        Empleado emp = repositorio.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usr = uRep.findByNombre(authentication.getName())
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario"));
        if (usr.getRol().equals(Rol.USER)) {
            if (!emp.getCreador().getId().equals(usr.getId())) {
                throw new UserActionNotAllowedException();
            }
        }
        repositorio.deleteById(id);
        if (repositorio.findById(id).isPresent()) {
            throw new RuntimeException("No se pudo borrar el empleado.");
        }
        return true;
    }

    // private Boolean checkUser(){

    // }
}
