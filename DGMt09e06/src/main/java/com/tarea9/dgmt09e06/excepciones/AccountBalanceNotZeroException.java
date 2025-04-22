package com.tarea9.dgmt09e06.excepciones;

public class AccountBalanceNotZeroException extends RuntimeException {
    public AccountBalanceNotZeroException() {
        super("La cuenta que desea borrar aún tiene saldo.");
    }
}
