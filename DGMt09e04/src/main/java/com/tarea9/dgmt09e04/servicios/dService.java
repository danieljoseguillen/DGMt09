package com.tarea9.dgmt09e04.servicios;

import java.util.List;

import com.tarea9.dgmt09e04.domain.Departamento;

public interface dService {

    List<Departamento>listAll();
    Departamento getById(long id);
    Departamento agregar(Departamento departamento);
    boolean modificar(Departamento departamento);
    boolean borrarPorId(long id);
}
