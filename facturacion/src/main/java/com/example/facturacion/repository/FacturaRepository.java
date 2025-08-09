package com.example.facturacion.repository;

import com.example.facturacion.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FacturaRepository extends JpaRepository<Factura, UUID> {
}
