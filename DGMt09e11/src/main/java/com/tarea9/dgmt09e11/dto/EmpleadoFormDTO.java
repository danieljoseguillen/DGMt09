package com.tarea9.dgmt09e11.dto;

import com.tarea9.dgmt09e11.modelos.Estado;
import com.tarea9.dgmt09e11.modelos.Genero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoFormDTO {
    private String nombre;
    private String correo;
    private Double salario;
    private Estado estado;
    private Genero genero;
}
