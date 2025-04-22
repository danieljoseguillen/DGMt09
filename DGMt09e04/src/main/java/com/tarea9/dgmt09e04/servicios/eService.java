package com.tarea9.dgmt09e04.servicios;

import java.util.List;

import com.tarea9.dgmt09e04.domain.Empleado;
import com.tarea9.dgmt09e04.modelos.Genero;

public interface eService {

    List<Empleado>listAll();
    List<Empleado>listSorted(Genero genero,String val);
    Empleado getById(long id);
    Empleado agregar(Empleado empleado);
    Empleado modificar(Empleado empleado);
    boolean borrarPorId(long id);
}
