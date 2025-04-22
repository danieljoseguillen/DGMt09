package com.tarea9.dgmt09e02.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HipotenusaDTO {
    private Integer numero1;
    private Integer numero2;
    private Double hipotenusa;
}
