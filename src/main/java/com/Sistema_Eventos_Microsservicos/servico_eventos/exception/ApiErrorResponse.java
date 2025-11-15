package com.Sistema_Eventos_Microsservicos.servico_eventos.exception;

import org.springframework.http.HttpStatus;
import java.time.Instant;

public record ApiErrorResponse(
    HttpStatus status,
    String message,
    Instant timestamp
) {}
