package com.tarea9.dgmt09e06.excepciones;

public class MovementDatabaseEmptyException extends RuntimeException {
    public MovementDatabaseEmptyException() {
        super("La base de datos no poseé movimientos para los datos indicados.");
    }
}
