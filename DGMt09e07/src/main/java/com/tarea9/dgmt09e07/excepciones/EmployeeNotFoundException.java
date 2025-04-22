package com.tarea9.dgmt09e07.excepciones;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("No se pudo encontrar el empleado con la id " + id);
    }
}
