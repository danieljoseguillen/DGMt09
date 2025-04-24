package com.tarea9.dgmt09e11.dto;

import com.tarea9.dgmt09e11.modelos.Genero;

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
