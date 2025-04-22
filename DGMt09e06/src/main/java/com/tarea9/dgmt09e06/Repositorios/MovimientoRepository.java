package com.tarea9.dgmt09e06.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea9.dgmt09e06.Domain.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento,Long>{
    List<Movimiento> findAllByOrderByFechaHoraDesc();
    List<Movimiento> findByCuentaIBANOrderByFechaHoraDesc(String IBAN);
    
}
