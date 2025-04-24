package com.tarea9.dgmt09e11.controladores;

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

import com.tarea9.dgmt09e11.domain.Empleado;
import com.tarea9.dgmt09e11.dto.EmpleadoDTO;
import com.tarea9.dgmt09e11.dto.EmpleadoFormDTO;
import com.tarea9.dgmt09e11.dto.sortDTO;
import com.tarea9.dgmt09e11.excepciones.EmployeeNotFoundException;
import com.tarea9.dgmt09e11.excepciones.EmptyEmployeeListException;
import com.tarea9.dgmt09e11.excepciones.UserActionNotAllowedException;
import com.tarea9.dgmt09e11.modelos.Estado;
import com.tarea9.dgmt09e11.servicios.EmpleadoDtoConverter;
import com.tarea9.dgmt09e11.servicios.eService;

import jakarta.validation.Valid;

@RequestMapping("/api/empleados")
@RestController
public class Controlador {

    @Autowired
    private EmpleadoDtoConverter empleadoDtoConverter;
    @Autowired
    private eService servicio;

    @GetMapping({ "", "/" })
    public List<EmpleadoDTO> getIndex() {
        try {
            List<Empleado> listaEmpleados = servicio.listAll();
            List<EmpleadoDTO> listaEmpleadoDto = empleadoDtoConverter.convertListaEmpleadoToDTO(listaEmpleados);
            return listaEmpleadoDto;
        } catch (EmptyEmployeeListException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/sort")
    public ResponseEntity<?> postIndexName(@RequestBody sortDTO sortform) {
        try {
            List<Empleado> listaSorted = servicio.listSorted(sortform.getSortgender(), sortform.getSortname());
            List<EmpleadoDTO> listaEmpleadoDto = empleadoDtoConverter.convertListaEmpleadoToDTO(listaSorted);
            return ResponseEntity.status(HttpStatus.OK).body(listaEmpleadoDto);
            // return ResponseEntity.status(HttpStatus.OK)
            // .body(servicio.listSorted(sortform.getSortgender(), sortform.getSortname()));
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

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> postedit(@Valid @RequestBody EmpleadoFormDTO empform, @PathVariable Long id) {
        try {
            servicio.getById(id);
            Empleado emp = empleadoDtoConverter.convertDtoToEmpleado(empform, id);
            Empleado emp2 = servicio.modificar(emp);
            return ResponseEntity.status(HttpStatus.OK).body(emp2);
        } catch (UserActionNotAllowedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> postAdd(@Valid @RequestBody EmpleadoFormDTO empform) {
        try {
            System.out.println("CONTROLADOR");
            Empleado emp = empleadoDtoConverter.convertDtoToEmpleado(empform, Estado.ACTIVO);
            System.out.println(emp);
            Empleado emp2 = servicio.agregar(emp);
            return ResponseEntity.status(HttpStatus.CREATED).body(emp2);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> getDelete(@PathVariable long id) {
        try {
            servicio.borrarPorId(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EmployeeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (UserActionNotAllowedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
