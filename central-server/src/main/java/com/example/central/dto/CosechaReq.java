package com.example.central.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CosechaReq(
        @NotNull Long agricultorId,
        @NotBlank String producto,
        @Positive double toneladas
) {}
