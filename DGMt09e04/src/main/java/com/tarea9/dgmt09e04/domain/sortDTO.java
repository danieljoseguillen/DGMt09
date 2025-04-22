package com.tarea9.dgmt09e04.domain;

import com.tarea9.dgmt09e04.modelos.Genero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class sortDTO {
    private String sortname;
    private Genero sortgender;
}
