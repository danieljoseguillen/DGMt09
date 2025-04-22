package com.tarea9.dgmt09e10.domain;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CambioData {
    private Double amount;
    private String base;
    private String date;
    private HashMap<String, Float> rates;
}
