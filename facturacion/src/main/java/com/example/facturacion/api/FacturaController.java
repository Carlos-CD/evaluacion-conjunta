package com.example.facturacion.api;

import com.example.facturacion.dto.FacturarReq;
import com.example.facturacion.dto.PagoReq;
import com.example.facturacion.model.Factura;
import com.example.facturacion.repository.FacturaRepository;
import com.example.facturacion.service.FacturacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {
    private final FacturacionService service;
    private final FacturaRepository repo;

    @PostMapping
    public Factura crear(@RequestBody @Valid FacturarReq r){
        return service.facturar(r);
    }

    @GetMapping
    public List<Factura> listar(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Factura porId(@PathVariable UUID id){
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Factura no existe"));
    }

    @PutMapping("/pagar")
    public Factura pagar(@RequestBody @Valid PagoReq r){
        return service.marcarPagada(r.facturaId());
    }
}
