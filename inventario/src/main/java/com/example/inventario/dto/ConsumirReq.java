package com.example.inventario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ConsumirReq(
        @NotBlank String producto,   // cacao, café, etc. (por ahora solo informativo)
        @Positive double toneladas   // > 0
) {}