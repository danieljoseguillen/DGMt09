package com.tarea9.dgmt09e07.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarea9.dgmt09e07.domain.Empleado;
import com.tarea9.dgmt09e07.domain.sortDTO;
import com.tarea9.dgmt09e07.servicios.eService;

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
        return ResponseEntity.status(HttpStatus.OK)
                .body(servicio.listSorted(sortform.getSortgender(), sortform.getSortname()));
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?> getInfo(@PathVariable long id) {
        Empleado emp = servicio.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(emp);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> postedit(@Valid @RequestBody Empleado empform) {
        servicio.getById(empform.getId());
        servicio.modificar(empform);
        return ResponseEntity.status(HttpStatus.OK).body(empform);
    }

    @PostMapping("/add")
    public ResponseEntity<?> postAdd(@Valid @RequestBody Empleado empform) {
        servicio.agregar(empform);
        return ResponseEntity.status(HttpStatus.CREATED).body(empform);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> getDelete(@PathVariable long id) {
        servicio.borrarPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
