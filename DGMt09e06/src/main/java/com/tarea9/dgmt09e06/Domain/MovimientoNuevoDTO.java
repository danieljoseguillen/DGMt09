package com.tarea9.dgmt09e06.Domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoNuevoDTO {

    @Max(value = 1000, message = "No se admiten ingresos de mas de 1000")
    @Min(value = -300, message = "No se admiten retiros de mas de 300")
    private Double saldo;

    @NotEmpty(message = "El IBAN no puede estar vacío.")
    @NotNull(message = "El IBAN es obligatorio.")
    private String iban;
    // Json es case sensitive por lo uqe tuve que pasar el campo a minusculas completamente
    // (para mejor sinceramente.)
}
