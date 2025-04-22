package com.tarea9.dgmt09e06.Servicios;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tarea9.dgmt09e06.Domain.Cuenta;
import com.tarea9.dgmt09e06.Domain.CuentaNuevaDTO;
import com.tarea9.dgmt09e06.Domain.Movimiento;
import com.tarea9.dgmt09e06.Domain.MovimientoNuevoDTO;

@Component
public class CuentaMovimientoDTOConverter {

    @Autowired
    private Servicio servicio;

    public Cuenta convertDTOToCuenta(CuentaNuevaDTO cDTO) {
        Cuenta cNueva = new Cuenta(
                cDTO.getIban(),
                cDTO.getAlias(),
                0.0d,
                new ArrayList<>());
        return cNueva;
    }

    public Movimiento convertDTOToMovimiento(MovimientoNuevoDTO mDTO) {
        Movimiento mNuevo = new Movimiento(
                null,
                LocalDateTime.now(),
                mDTO.getSaldo(),
                servicio.getById(mDTO.getIban()));
                System.out.println(mNuevo);
        return mNuevo;
    }
}
