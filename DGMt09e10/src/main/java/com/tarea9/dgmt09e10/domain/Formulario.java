package com.tarea9.dgmt09e10.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Formulario {
    @NotNull(message = "El campo no puede estár vacío.")
    @Min(value = 0,message = "El importe minimo debe ser 0.")
    private Double importe;
    private String origen;
    private String destino;
}
