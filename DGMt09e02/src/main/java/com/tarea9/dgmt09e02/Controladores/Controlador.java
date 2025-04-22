package com.tarea9.dgmt09e02.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tarea9.dgmt09e02.Servicios.CalculosService;
import com.tarea9.dgmt09e02.domain.DivisoresDTO;
import com.tarea9.dgmt09e02.domain.HipotenusaDTO;
import com.tarea9.dgmt09e02.domain.PrimoDTO;

@RestController
public class Controlador {

    @Autowired
    private CalculosService servicio;

    @GetMapping("/calculos/primo")
    public ResponseEntity<?> primos(@RequestParam(required = false) Integer numero, Model model) {
        try {
            PrimoDTO esPrimo = new PrimoDTO(numero, false);
            if (servicio.esPrimo(numero)) {
                esPrimo.setPrimo(true);
                return ResponseEntity.status(HttpStatus.OK).body(esPrimo);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(esPrimo);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/calculos/hipotenusa/{val1}/{val2}")
    public ResponseEntity<?> hipotenusa(@PathVariable Integer val1, @PathVariable Integer val2, Model model) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new HipotenusaDTO(val1, val2, servicio.calchipotenusa(val1, val2)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/calculos/divisores/{valor}")
    public ResponseEntity<?> divisores(@PathVariable Integer valor, Model model) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new DivisoresDTO(valor,servicio.calcdivisores(valor)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
