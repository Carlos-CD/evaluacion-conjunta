package com.example.facturacion.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record PagoReq(@NotNull UUID facturaId) {}