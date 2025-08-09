package com.example.central.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Cosecha {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private Agricultor agricultor;

    @Column(nullable=false)
    private String producto;

    @Column(nullable=false, precision = 12, scale = 3)
    private BigDecimal toneladas;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Estado estado;

    private LocalDateTime creadaEn;

    @PrePersist void onCreate(){ this.creadaEn = LocalDateTime.now(); }

    public enum Estado { REGISTRADA, INVENTARIO_AJUSTADO, FACTURADA, PAGADA }
}
