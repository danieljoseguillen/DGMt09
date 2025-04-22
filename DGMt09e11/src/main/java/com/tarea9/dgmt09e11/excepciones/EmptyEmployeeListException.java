package com.tarea9.dgmt09e11.excepciones;

public class EmptyEmployeeListException extends RuntimeException {
    public EmptyEmployeeListException() {
        super("La busqueda realizada no encontró empleados.");
    }
}
