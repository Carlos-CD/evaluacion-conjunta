package com.example.inventario.api;

import com.example.inventario.dto.ConsumirReq;
import com.example.inventario.dto.InsumoCreateReq;
import com.example.inventario.dto.InsumoUpdateReq;
import com.example.inventario.model.Insumo;
import com.example.inventario.service.InsumoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InsumoController {
    private final InsumoService service;

    // CRUD básico
    @GetMapping("/insumos")
    public List<Insumo> listar() { return service.listar(); }

    @PostMapping("/insumos")
    public Insumo crear(@RequestBody @Valid InsumoCreateReq r) {
        return service.crear(r);
    }

    @PutMapping("/insumos/{id}")
    public Insumo actualizar(@PathVariable UUID id, @RequestBody @Valid InsumoUpdateReq r) {
        return service.actualizar(id, r);
    }

    @PutMapping("/insumos/{id}/ajustar/{delta}")
    public Insumo ajustar(@PathVariable UUID id, @PathVariable double delta) {
        return service.ajustarStock(id, delta); // delta negativo descuenta
    }

    // Integración con otros MS: consumir insumos por toneladas
    @PostMapping("/inventario/consumir")
    public void consumir(@RequestBody @Valid ConsumirReq r) {
        service.consumirPorTonelada(r);
    }
}
