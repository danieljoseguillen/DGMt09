package com.tarea9.dgmt09e06.excepciones;

public class MovementBalanceOutOfBoundsException extends RuntimeException {
    public MovementBalanceOutOfBoundsException() {
        super("El monto que desea retirar es superior al saldo actual de la cuenta.");
    }
}
