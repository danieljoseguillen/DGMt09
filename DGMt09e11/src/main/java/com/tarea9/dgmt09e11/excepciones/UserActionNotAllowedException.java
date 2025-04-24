package com.tarea9.dgmt09e11.excepciones;

public class UserActionNotAllowedException extends RuntimeException {
    public UserActionNotAllowedException() {
        super("Solo pueden modificar/editar empleados aquellos usuarios que los crearon.");
    }
}
