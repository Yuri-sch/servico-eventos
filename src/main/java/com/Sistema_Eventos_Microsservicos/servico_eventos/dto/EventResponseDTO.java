package com.Sistema_Eventos_Microsservicos.servico_eventos.dto;

import com.Sistema_Eventos_Microsservicos.servico_eventos.model.Event;
import java.time.OffsetDateTime;

public record EventResponseDTO(
    String id,
    String eventName,
    OffsetDateTime eventDate,
    String description,
    String duration,
    String category,
    String eventLocal
) {
    // Método "factory" para converter da Entidade para o DTO
    public static EventResponseDTO fromEntity(Event event) {
        return new EventResponseDTO(
            event.getId(),
            event.getEventName(),
            event.getEventDate(),
            event.getDescription(),
            event.getDuration(),
            event.getCategory(),
            event.getEventLocal()
        );
    }
}
