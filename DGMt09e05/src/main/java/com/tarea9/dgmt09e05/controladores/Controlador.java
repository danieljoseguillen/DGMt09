package com.tarea9.dgmt09e05.controladores;

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
import org.springframework.web.server.ResponseStatusException;

import com.tarea9.dgmt09e05.domain.Empleado;
import com.tarea9.dgmt09e05.domain.sortDTO;
import com.tarea9.dgmt09e05.excepciones.EmployeeNotFoundException;
import com.tarea9.dgmt09e05.excepciones.EmptyEmployeeListException;
import com.tarea9.dgmt09e05.servicios.eService;

import jakarta.validation.Valid;

@RequestMapping("/empleados")
@RestController
public class Controlador {

    @Autowired
    private eService servicio;

    @GetMapping({ "", "/" })
    public List<Empleado> getIndex() {
        try {
            return servicio.listAll();
        } catch (EmptyEmployeeListException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            // TEAPOT! Who would believe it was an actual http status? I bet someone was
            // bored at some point when creating this piece of code.
            // throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/sort")
    public ResponseEntity<?> postIndexName(@RequestBody sortDTO sortform) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(servicio.listSorted(sortform.getSortgender(), sortform.getSortname()));
        } catch (EmptyEmployeeListException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?> getInfo(@PathVariable long id) {
        try {
            Empleado emp = servicio.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(emp);
        } catch (EmployeeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<?> postedit(@Valid @RequestBody Empleado empform) {
        try {
            servicio.getById(empform.getId());
            servicio.modificar(empform);
            return ResponseEntity.status(HttpStatus.OK).body(empform);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> postAdd(@Valid @RequestBody Empleado empform) {
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
        } catch (EmployeeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
