package com.tarea9.dgmt09e06.Servicios;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.tarea9.dgmt09e06.Domain.Cuenta;
import com.tarea9.dgmt09e06.Domain.Movimiento;
import com.tarea9.dgmt09e06.Repositorios.CuentaRepository;
import com.tarea9.dgmt09e06.Repositorios.MovimientoRepository;
import com.tarea9.dgmt09e06.excepciones.AccountBalanceNotZeroException;
import com.tarea9.dgmt09e06.excepciones.AccountDatabaseEmptyException;
import com.tarea9.dgmt09e06.excepciones.AccountNotFoundException;
import com.tarea9.dgmt09e06.excepciones.MovementBalanceOutOfBoundsException;
import com.tarea9.dgmt09e06.excepciones.MovementDatabaseEmptyException;

@org.springframework.stereotype.Service
public class ServiceImpl implements Servicio {

    @Autowired
    private CuentaRepository cRepository;

    @Autowired
    private MovimientoRepository mRepository;

    public List<Cuenta> listAllCuentas() {
        List<Cuenta> lista = cRepository.findAll();
        if (lista.isEmpty()) {
            throw new AccountDatabaseEmptyException();
        }
        return lista;
    }

    public Cuenta getById(String IBAN) {
        return cRepository.findById(IBAN).orElseThrow(() -> new AccountNotFoundException());
    }

    public Cuenta agregarCuenta(Cuenta cuenta) {
        try {
            Cuenta c1 = cRepository.findById(cuenta.getIBAN()).orElse(null);
            if (c1 != null) {
                throw new RuntimeException("Ya existe una cuenta con ese IBAN");
            }
            cuenta.setSaldo(0d);
            return cRepository.save(cuenta);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo agregar la cuenta.");
        }
    }

    public Cuenta modificarCuenta(Cuenta cuenta) {
        try {
            return cRepository.save(cuenta);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo modificar la cuenta.");
        }
    }

    public boolean borrarCuenta(String IBAN) {
        Cuenta cuenta = this.getById(IBAN);
        if (cuenta.getSaldo() > 0) {
            throw new AccountBalanceNotZeroException();
        } else {
            cRepository.deleteById(IBAN);
            return true;
        }
    }

    public List<Movimiento> listAllAcountMovements(String IBAN) {
        List<Movimiento> lista;
        if (IBAN == null) {
            lista = mRepository.findAllByOrderByFechaHoraDesc();
        } else {
            lista = mRepository.findByCuentaIBANOrderByFechaHoraDesc(IBAN);
        }
        if (lista.isEmpty()) {
            throw new MovementDatabaseEmptyException();
        }
        return lista;

    }

    public String generadorIBAN() {
        String ibn;
        Cuenta cn;
        do {
            ibn = "ES" + String.format("%020d", Math.abs(new Random().nextLong()));
            cn = cRepository.findById(ibn).orElse(null);
        } while (cn != null);
        return ibn;

    }

    public Movimiento agregarMovimiento(Movimiento mov) {
        try {
            // Proceso para agregar la fecha y sustraer o agregar saldo tras guardar el
            // movimiento
            mov.setFechaHora(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            Cuenta cuenta = mov.getCuenta();
            System.out.println(cuenta.getSaldo() + mov.getSaldo());
            if (cuenta.getSaldo() + mov.getSaldo() < 0) {
                throw new MovementBalanceOutOfBoundsException();
            }
            cuenta.setSaldo(cuenta.getSaldo() + mov.getSaldo());
            Movimiento movem = mRepository.save(mov);
            modificarCuenta(cuenta);
            return movem;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
