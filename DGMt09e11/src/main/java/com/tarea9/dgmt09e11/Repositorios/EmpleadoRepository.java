package com.tarea9.dgmt09e11.Repositorios;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea9.dgmt09e11.domain.Empleado;
import com.tarea9.dgmt09e11.modelos.Genero;

public interface EmpleadoRepository extends JpaRepository <Empleado, Long> {
    List<Empleado> findByGenero(Genero genero);
    List<Empleado> findByNombreContainingIgnoreCase(String nombre);
    List<Empleado> findByGeneroAndNombreContainingIgnoreCase(Genero genero, String nombre);
}
