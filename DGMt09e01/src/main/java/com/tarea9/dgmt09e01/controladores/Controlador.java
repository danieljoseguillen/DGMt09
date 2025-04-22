package com.tarea9.dgmt09e01.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarea9.dgmt09e01.domain.Empleado;
import com.tarea9.dgmt09e01.domain.sortDTO;
import com.tarea9.dgmt09e01.servicios.eService;

import jakarta.validation.Valid;
@RequestMapping("/empleados")
@RestController
public class Controlador {

    @Autowired
    private eService servicio;

    @GetMapping({ "", "/" })
    public List<Empleado> getIndex() {
        return servicio.listAll();
    }

    @PostMapping("/sort")
    public ResponseEntity<?> postIndexName(@RequestBody sortDTO sortform) {
        System.out.println(sortform.getSortgender());
        System.out.println(sortform.getSortname());
        return ResponseEntity.status(HttpStatus.OK).body(servicio.listSorted(sortform.getSortgender(),sortform.getSortname()));
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?> getInfo(@PathVariable long id) {
        try {
            Empleado emp = servicio.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(emp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<?> postedit(@Valid @RequestBody Empleado empform, BindingResult bindingResult) {
        try {
            servicio.getById(empform.getId());
            servicio.modificar(empform);
            return ResponseEntity.status(HttpStatus.OK).body(empform);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> postAdd(@Valid @RequestBody Empleado empform, BindingResult bindingResult) {
        try {
            servicio.agregar(empform);
            return ResponseEntity.status(HttpStatus.CREATED).body(empform);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> getDelete(@PathVariable long id) {
        try {
            servicio.borrarPorId(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
