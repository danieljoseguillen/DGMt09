package com.tarea9.dgmt09e05.servicios;

import java.util.List;

import com.tarea9.dgmt09e05.domain.Empleado;
import com.tarea9.dgmt09e05.modelos.Genero;

public interface eService {

    List<Empleado>listAll();
    List<Empleado>listSorted(Genero genero,String val);
    Empleado getById(long id);
    boolean agregar(Empleado empleado);
    boolean modificar(Empleado empleado);
    boolean borrarPorId(long id);
}
