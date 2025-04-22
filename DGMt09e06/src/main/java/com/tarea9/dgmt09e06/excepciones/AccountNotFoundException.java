package com.tarea9.dgmt09e06.excepciones;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException() {
        super("La cuenta indicada no se pudo encontrar en labase de datos.");
    }
}