package com.Sistema_Eventos_Microsservicos.servico_eventos.exception;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String id) {
        super("Event not found with ID: " + id);
    }
}
