package com.tarea9.dgmt09e04.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarea9.dgmt09e04.domain.Empleado;
import com.tarea9.dgmt09e04.modelos.Genero;
import com.tarea9.dgmt09e04.repositorios.EmpleadoRepository;

@Service
public class eServiceImpl implements eService {

    @Autowired
    EmpleadoRepository repositorio;

    public List<Empleado> listAll() {
        return repositorio.findAll();
    }

    public List<Empleado> listSorted(Genero genero, String val) {
        if (genero == null) {
            return repositorio.findByNombreContainingIgnoreCase(val);
        } else if (val.isEmpty() || val == null) {
            return repositorio.findByGenero(genero);
        } else {
            return repositorio.findByGeneroAndNombreContainingIgnoreCase(genero, val);
        }
    }

    public List<Empleado> listGender(Genero genero) {
        return repositorio.findByGenero(genero);
    }

    public Empleado getById(long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontraron empleados con la ID indicada."));
    }

    public Empleado agregar(Empleado empleado) {
        try {
            return repositorio.save(empleado);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo agregar el empleado."+e.getMessage());
        }
    }

    public Empleado modificar(Empleado empleado) {
        try {
            return repositorio.save(empleado);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo modificar el empleado.");
        }
    }

    public boolean borrarPorId(long id) {

        if (repositorio.findById(id).isPresent()) {
            repositorio.deleteById(id);
            return true;
        }

        throw new RuntimeException("No existe el empleado.");
    }

}
