package com.tarea9.dgmt09e03.Controladores;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tarea9.dgmt09e03.Servicios.Servicios;
import com.tarea9.dgmt09e03.Servicios.formInfo;

import jakarta.validation.Valid;

@RestController
public class Controlador {

    @Autowired
    private Servicios servicio;

    // Post mapping del formulario.
    @PostMapping("/calculos/fecha")
    public ResponseEntity<?> resultado(@Valid @RequestBody formInfo forminfo, BindingResult bindingResult,
            Model model) {
        try {
            Map<String, String> mapa = new LinkedHashMap<>();
            mapa.put("Fecha 1", forminfo.getFecha1().toString());
            mapa.put("Fecha 2", forminfo.getFecha2().toString());
            mapa.put("Dias entre las fechas", servicio.calcdias(forminfo.getFecha1(), forminfo.getFecha2()).toString());
            return ResponseEntity.status(HttpStatus.OK).body(mapa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}