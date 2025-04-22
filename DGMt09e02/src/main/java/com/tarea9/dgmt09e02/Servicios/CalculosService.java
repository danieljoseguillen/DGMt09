package com.tarea9.dgmt09e02.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CalculosService {

    public boolean esPrimo(int n) {
        if (n <= 0) {
            throw new RuntimeException("Deben ingresarse valores positivos para la operación de numeros primos.");
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public double calchipotenusa(int val1, int val2) {
        if (val1 <= 0 || val2 <= 0) {
            throw new RuntimeException("Deben ingresarse valores positivos para la operación de hipotenusa.");
        }
        return Math.hypot(val1, val2);
    }

    public List<Integer> calcdivisores(int val) {
        List<Integer> divisores = new ArrayList<>();
        if (val <= 0) {
            throw new RuntimeException("Deben ingresarse valores positivos para la operación de divisores.");
        }
        for (int i = 1; i <= val; i++) {
            if (val % i == 0) {
                divisores.add(i);
                System.out.println(i);
            }
        }
        return divisores;
    }

}
