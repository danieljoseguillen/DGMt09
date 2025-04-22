package com.tarea9.dgmt09e10.servicios;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Divisas {
    public static final Map<String, String> divisas = Map.of(
        "EUR", "Euros",
        "USD", "Dólares estadounidenses",
        "GBP", "Libras esterlinas",
        "JPY", "Yenes"
    );
    // public static final Map<String, String> divisas = Map.ofEntries(
    //     Map.entry("AUD", "Dólares australianos"),
    //     Map.entry("BGN", "Levas búlgaras"),
    //     Map.entry("BRL", "Reales brasileños"),
    //     Map.entry("CAD", "Dólares canadienses"),
    //     Map.entry("CHF", "Francos suizos"),
    //     Map.entry("CNY", "Yuanes chinos"),
    //     Map.entry("CZK", "Coronas checas"),
    //     Map.entry("DKK", "Coronas danesas"),
    //     Map.entry("EUR", "Euros"),
    //     Map.entry("HKD", "Dólares de Hong Kong"),
    //     Map.entry("HUF", "Florines húngaros"),
    //     Map.entry("IDR", "Rupias indonesias"),
    //     Map.entry("ILS", "Nuevos séqueles israelíes"),
    //     Map.entry("INR", "Rupias indias"),
    //     Map.entry("ISK", "Coronas islandesas"),
    //     Map.entry("JPY", "Yenes"),
    //     Map.entry("KRW", "Wones surcoreanos"),
    //     Map.entry("MXN", "Pesos mexicanos"),
    //     Map.entry("MYR", "Ringgits malasios"),
    //     Map.entry("NOK", "Coronas noruegas"),
    //     Map.entry("NZD", "Dólares neozelandeses"),
    //     Map.entry("PHP", "Pesos filipinos"),
    //     Map.entry("PLN", "Zlotys polacos"),
    //     Map.entry("RON", "Leus rumanos"),
    //     Map.entry("SEK", "Coronas suecas"),
    //     Map.entry("SGD", "Dólares singapurenses"),
    //     Map.entry("THB", "Baht tailandeses"),
    //     Map.entry("TRY", "Liras turcas"),
    //     Map.entry("USD", "Dólares estadounidenses"),
    //     Map.entry("ZAR", "Rands sudafricanos")
    // );
}
