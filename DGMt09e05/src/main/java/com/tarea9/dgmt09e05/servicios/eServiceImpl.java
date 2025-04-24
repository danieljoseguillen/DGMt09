package com.tarea9.dgmt09e05.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarea9.dgmt09e05.Repositorios.EmpleadoRepository;
import com.tarea9.dgmt09e05.domain.Empleado;
import com.tarea9.dgmt09e05.excepciones.EmployeeNotFoundException;
import com.tarea9.dgmt09e05.excepciones.EmptyEmployeeListException;
import com.tarea9.dgmt09e05.modelos.Genero;

@Service
public class eServiceImpl implements eService {

    @Autowired
    EmpleadoRepository repositorio;

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

    public boolean agregar(Empleado empleado) {
        try {
            repositorio.save(empleado);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo agregar el empleado.");
        }
        return true;
    }

    public boolean modificar(Empleado empleado) {
        try {
            repositorio.save(empleado);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo modificar el empleado.");
        }
        return true;
    }

    public boolean borrarPorId(long id) {
        repositorio.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        repositorio.deleteById(id);
        if (repositorio.findById(id).isPresent()) {
            throw new RuntimeException("No se pudo borrar el empleado.");
        }
        return true;
    }

}
