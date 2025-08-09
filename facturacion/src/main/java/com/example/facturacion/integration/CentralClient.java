package com.example.facturacion.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import java.util.UUID;

@Component
public class CentralClient {
    private final RestClient client;

    public CentralClient(@Value("${app.central-base-url}") String baseUrl) {
        this.client = RestClient.create(baseUrl);
    }

    public void marcarFacturada(UUID cosechaId) {
        client.put()
                .uri("/api/cosechas/{id}/estado/{estado}", cosechaId, "FACTURADA")
                .retrieve()
                .toBodilessEntity();
    }

    public void marcarPagada(UUID cosechaId) {
        client.put()
                .uri("/api/cosechas/{id}/estado/{estado}", cosechaId, "PAGADA")
                .retrieve()
                .toBodilessEntity();
    }
}