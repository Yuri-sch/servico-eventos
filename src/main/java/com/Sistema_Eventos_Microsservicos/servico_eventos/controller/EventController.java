package com.Sistema_Eventos_Microsservicos.servico_eventos.controller;

import com.Sistema_Eventos_Microsservicos.servico_eventos.dto.EventCreateDTO;
import com.Sistema_Eventos_Microsservicos.servico_eventos.dto.EventResponseDTO;
import com.Sistema_Eventos_Microsservicos.servico_eventos.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/events") // Endpoint em inglês
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable String id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(@Valid @RequestBody EventCreateDTO dto) {
        EventResponseDTO newEvent = eventService.createEvent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEvent);
    }
}