package com.tarea9.dgmt09e06.excepciones;

public class AccountDatabaseEmptyException extends RuntimeException {
    public AccountDatabaseEmptyException() {
        super("La base de datos de cuentas está vacía.");
    }
}