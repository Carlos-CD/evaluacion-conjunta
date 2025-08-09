package com.example.inventario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record InsumoUpdateReq(
        @NotBlank String unidad,
        @PositiveOrZero double stock
) {}