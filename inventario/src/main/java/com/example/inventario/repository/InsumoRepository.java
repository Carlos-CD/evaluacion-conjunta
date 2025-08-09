package com.example.inventario.repository;

import com.example.inventario.model.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InsumoRepository extends JpaRepository<Insumo, UUID> {
    Optional<Insumo> findByNombreIgnoreCase(String nombre);
}
