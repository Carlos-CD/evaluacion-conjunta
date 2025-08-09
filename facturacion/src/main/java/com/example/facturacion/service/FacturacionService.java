package com.example.facturacion.service;

import com.example.facturacion.dto.FacturarReq;
import com.example.facturacion.integration.CentralClient;
import com.example.facturacion.model.Factura;
import com.example.facturacion.repository.FacturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FacturacionService {
    private final FacturaRepository repo;
    private final CentralClient central;

    @Value("#{${app.precios}}")
    private Map<String, Double> precios;

    @Transactional
    public Factura facturar(FacturarReq r){
        double precio = precios.getOrDefault(r.producto(), 100.0);
        double total = precio * r.toneladas();

        Factura f = repo.save(Factura.builder()
                .cosechaId(r.cosechaId())
                .producto(r.producto())
                .toneladas(r.toneladas())
                .montoTotal(total)
                .pagada(false)
                .build());

        central.marcarFacturada(r.cosechaId()); // side-effect hacia Central
        return f;
    }

    @Transactional
    public Factura marcarPagada(UUID facturaId){
        Factura f = repo.findById(facturaId)
                .orElseThrow(() -> new IllegalArgumentException("Factura no existe"));
        if (!f.isPagada()){
            f.setPagada(true);
            central.marcarPagada(f.getCosechaId()); // side-effect hacia Central
        }
        return f;
    }
}

