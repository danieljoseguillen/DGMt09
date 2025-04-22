package com.tarea9.dgmt09e06.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tarea9.dgmt09e06.Domain.Cuenta;
import com.tarea9.dgmt09e06.Domain.CuentaNuevaDTO;
import com.tarea9.dgmt09e06.Domain.Movimiento;
import com.tarea9.dgmt09e06.Domain.MovimientoNuevoDTO;
import com.tarea9.dgmt09e06.Servicios.CuentaMovimientoDTOConverter;
import com.tarea9.dgmt09e06.Servicios.Servicio;

import jakarta.validation.Valid;

@RestController
public class Controlador {

    @Autowired
    private Servicio servicio;

    @Autowired
    CuentaMovimientoDTOConverter converter;

    // Area cuentas
    @GetMapping("/cuentas")
    public List<Cuenta> getHome(Model model) {
        try {
            return servicio.listAllCuentas();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/cuentas/new")
    public ResponseEntity<?> postSignUp(@Valid @RequestBody CuentaNuevaDTO cuentaForm) {
        try {
            Cuenta cNueva = converter.convertDTOToCuenta(cuentaForm);
            return ResponseEntity.status(HttpStatus.CREATED).body(servicio.agregarCuenta(cNueva));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/cuentas/delete/{iban}")
    public ResponseEntity<?> getDelete(@PathVariable String iban) {
        try {
            servicio.borrarCuenta(iban);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    // Area movimientos
    @GetMapping("/movements/{iban}")
    public List<Movimiento> getMovements(@PathVariable String iban) {
        try {
            return servicio.listAllAcountMovements(iban);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/movements/new")
    public ResponseEntity<?> postNewMove(@Valid @RequestBody MovimientoNuevoDTO moveForm) {
        System.out.println("DTO recibido: " + moveForm);
        try {
            Movimiento moviNuevo = converter.convertDTOToMovimiento(moveForm);
            return ResponseEntity.status(HttpStatus.CREATED).body(servicio.agregarMovimiento(moviNuevo));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}