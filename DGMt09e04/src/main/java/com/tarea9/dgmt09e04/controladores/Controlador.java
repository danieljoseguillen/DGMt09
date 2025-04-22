package com.tarea9.dgmt09e04.controladores;

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

import com.tarea9.dgmt09e04.domain.Empleado;
import com.tarea9.dgmt09e04.domain.EmpleadoDTO;
import com.tarea9.dgmt09e04.domain.EmpleadoFormDTO;
import com.tarea9.dgmt09e04.domain.sortDTO;
import com.tarea9.dgmt09e04.modelos.Estado;
import com.tarea9.dgmt09e04.servicios.EmpleadoDtoConverter;
import com.tarea9.dgmt09e04.servicios.eService;

import jakarta.validation.Valid;

@RequestMapping("/empleados")
@RestController
public class Controlador {

    @Autowired
    private EmpleadoDtoConverter empleadoDtoConverter;
    @Autowired
    private eService servicio;

    @GetMapping({ "", "/" })
    public ResponseEntity<?> getIndex() {
        List<Empleado> listaEmpleados = servicio.listAll();
        if (listaEmpleados.isEmpty())
            return ResponseEntity.notFound().build();
        else {
            List<EmpleadoDTO> listaEmpleadoDto = empleadoDtoConverter.convertListaEmpleadoToDTO(listaEmpleados);
            return ResponseEntity.status(HttpStatus.OK).body(listaEmpleadoDto);
        }
    }

    @PostMapping("/sort")
    public ResponseEntity<?> postIndexName(@RequestBody sortDTO sortform) {
        System.out.println(sortform.getSortgender());
        System.out.println(sortform.getSortname());
        List<Empleado> listaSorted = servicio.listSorted(sortform.getSortgender(), sortform.getSortname());
        if (listaSorted.isEmpty())
            return ResponseEntity.notFound().build();
        else {
            List<EmpleadoDTO> listaEmpleadoDto = empleadoDtoConverter.convertListaEmpleadoToDTO(listaSorted);
            return ResponseEntity.status(HttpStatus.OK).body(listaEmpleadoDto);
        }
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

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> postedit(@Valid @RequestBody EmpleadoFormDTO empform, BindingResult bindingResult,@PathVariable Long id) {
        try {
            servicio.getById(id);
            Empleado emp = empleadoDtoConverter.convertDtoToEmpleado(empform,id);
            Empleado emp2 = servicio.modificar(emp);
            return ResponseEntity.status(HttpStatus.OK).body(emp2);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> postAdd(@Valid @RequestBody EmpleadoFormDTO empform, BindingResult bindingResult) {
        try {
            Empleado emp = empleadoDtoConverter.convertDtoToEmpleado(empform,Estado.ACTIVO);
            Empleado emp2 = servicio.agregar(emp);
            return ResponseEntity.status(HttpStatus.CREATED).body(emp2);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> getDelete(@PathVariable Long id) {
        try {
            servicio.borrarPorId(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
