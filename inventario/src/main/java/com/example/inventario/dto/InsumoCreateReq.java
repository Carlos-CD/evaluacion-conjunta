package com.example.inventario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record InsumoCreateReq(
        @NotBlank String nombre,
        @NotBlank String unidad,
        @PositiveOrZero double stock
) {
}
