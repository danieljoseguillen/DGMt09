package com.tarea9.dgmt09e06.Domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaNuevaDTO {

    @NotEmpty(message = "El IBAN no puede estar vacío.")
    @NotNull(message = "El IBAN es obligatorio.")
    private String iban;

    @Size(min = 3, message = "El alias debe tener al menos 3 caracteres.")
    @NotEmpty(message = "El alias no puede estar vacío.")
    @NotNull(message = "El alias es obligatorio.")
    private String alias;
}
