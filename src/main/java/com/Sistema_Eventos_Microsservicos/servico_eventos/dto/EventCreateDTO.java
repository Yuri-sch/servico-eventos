package com.Sistema_Eventos_Microsservicos.servico_eventos.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;

public record EventCreateDTO(
    @NotBlank(message = "Nome do evento não pode ser nulo")
    @Size(min = 3, max = 45)
    String eventName,

    @NotNull(message = "A data do evento não pode ser nula")
    @FutureOrPresent(message = "Evento não pode ser no passado")
    OffsetDateTime eventDate,

    String description,
    String duration,
    String category,
    String eventLocal
) {}