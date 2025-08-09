package com.example.inventario.service;

import com.example.inventario.dto.ConsumirReq;
import com.example.inventario.dto.InsumoCreateReq;
import com.example.inventario.dto.InsumoUpdateReq;
import com.example.inventario.model.Insumo;
import com.example.inventario.repository.InsumoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InsumoService {
    private final InsumoRepository repo;

    public List<Insumo> listar() {
        return repo.findAll();
    }

    @Transactional
    public Insumo crear(InsumoCreateReq r) {
        repo.findByNombreIgnoreCase(r.nombre()).ifPresent(i -> {
            throw new IllegalArgumentException("El insumo ya existe: " + r.nombre());
        });
        return repo.save(Insumo.builder()
                .nombre(r.nombre())
                .unidad(r.unidad())
                .stock(r.stock())
                .build());
    }

    @Transactional
    public Insumo actualizar(UUID id, InsumoUpdateReq r) {
        Insumo i = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Insumo no encontrado"));
        i.setUnidad(r.unidad());
        i.setStock(r.stock());
        return i;
    }

    @Transactional
    public Insumo ajustarStock(UUID id, double delta) {
        Insumo i = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Insumo no encontrado"));
        double nuevo = i.getStock() + delta;  // delta negativo descuenta
        if (nuevo < 0) throw new IllegalArgumentException("Stock insuficiente");
        i.setStock(nuevo);
        return i;
    }

    @Transactional
    public void consumirPorTonelada(ConsumirReq r) {
        double t = r.toneladas();
        descontar("semilla",      -5.0 * t);
        descontar("fertilizante", -2.0 * t);
    }

    private void descontar(String nombre, double deltaNegativo) {
        Insumo insumo = repo.findByNombreIgnoreCase(nombre)
                .orElseThrow(() -> new IllegalArgumentException("Falta insumo: " + nombre));
        double nuevo = insumo.getStock() + deltaNegativo;
        if (nuevo < 0) throw new IllegalArgumentException("Stock insuficiente de " + nombre);
        insumo.setStock(nuevo);
    }
}
