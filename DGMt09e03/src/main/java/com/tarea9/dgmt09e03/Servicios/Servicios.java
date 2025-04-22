package com.tarea9.dgmt09e03.Servicios;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class Servicios {
    private String resultado;

    //Cuantos días hay entre ambas fechas.
    public Long calcdias (LocalDate fecha1, LocalDate fecha2){
        basicval(fecha1, fecha2);
        return ChronoUnit.DAYS.between(fecha1, fecha2);
    }
    //Lista de los años bisiestos comprendidos entre las fechas introducidas (ambas incluidas).
    public ArrayList<Integer> listbisiestos(LocalDate fecha1, LocalDate fecha2){
        basicval(fecha1, fecha2);
        int y1=fecha1.getYear();
        int y2=fecha2.getYear();
        ArrayList <Integer> listado = new ArrayList<>();
        for(;y1<=y2;y1++){
            boolean bisiesto = YearMonth.of(y1, 2).lengthOfMonth() == 29;
            if (bisiesto) {
                listado.add(y1);
            }
        }
        return listado;
    }

    //En cuantos años, entre ambas fechas, coincidió que el 1 de enero fuese domingo.
    public int enerodom(LocalDate fecha1, LocalDate fecha2){
        basicval(fecha1, fecha2);
        int val=0;
        for(int i=fecha1.getYear(),j=fecha2.getYear();i<=j;i++){
            LocalDate thisyear = LocalDate.of(i, 1, 1);
            if (thisyear.getDayOfWeek().getValue() == 7) {
                val++;
            }
        }
        return val;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    //Valida si la segunda fecha es menor que la primera... podría hacer que si se da el caso retornara un booleano para cambiarlas de lugar.
    private void basicval (LocalDate fecha1, LocalDate fecha2){
        if (fecha2.isBefore(fecha1)) {
            throw new RuntimeException("Error: la fecha 2 es mayor que la 1.");
        }
    }
}
