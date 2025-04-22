package com.tarea9.dgmt09e10.servicios;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.tarea9.dgmt09e10.domain.CambioData;
import com.tarea9.dgmt09e10.domain.Formulario;

@Service
public class Servicio {
    private RestClient restClient = RestClient.create();

    public Double calcular(Formulario formu) {
        String origen = formu.getOrigen();
        String destino = formu.getDestino();
        CambioData datos = restClient.get()
                .uri("https://api.frankfurter.app/latest?from={origen}&to={destino}", origen, destino)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(CambioData.class);
        
        return formu.getImporte() * datos.getRates().get(formu.getDestino());
    }
}
