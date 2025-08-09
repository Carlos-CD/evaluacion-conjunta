package com.example.inventario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Insumo {
    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nombre;

    @NotBlank
    @Column(nullable = false)
    private String unidad;

    @PositiveOrZero
    @Column(nullable = false)
    private double stock;
}