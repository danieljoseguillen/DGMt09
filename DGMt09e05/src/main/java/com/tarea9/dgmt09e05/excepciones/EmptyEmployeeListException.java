package com.tarea9.dgmt09e05.excepciones;

public class EmptyEmployeeListException extends RuntimeException {
    public EmptyEmployeeListException() {
        super("La busqueda realizada no encontró empleados.");
    }
}
