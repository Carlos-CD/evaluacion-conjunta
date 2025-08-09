package com.example.facturacion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;

public record FacturarReq(
        @NotNull UUID cosechaId,
        @NotBlank String producto,
        @Positive double toneladas
) {}