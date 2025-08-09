package com.example.facturacion.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Factura {
    @Id @GeneratedValue
    private UUID id;

    @Column(nullable=false)
    private UUID cosechaId;

    @Column(nullable=false)
    private String producto;

    @Column(nullable=false)
    private double toneladas;

    @Column(nullable=false)
    private double montoTotal;

    @Column(nullable=false)
    private boolean pagada;

    private LocalDateTime creadaEn;

    @PrePersist void onCreate(){ this.creadaEn = LocalDateTime.now(); }
}
