package com.Sistema_Eventos_Microsservicos.servico_eventos.service;

import com.Sistema_Eventos_Microsservicos.servico_eventos.dto.EventCreateDTO;
import com.Sistema_Eventos_Microsservicos.servico_eventos.dto.EventResponseDTO;
import com.Sistema_Eventos_Microsservicos.servico_eventos.exception.EventNotFoundException;
import com.Sistema_Eventos_Microsservicos.servico_eventos.model.Event;
import com.Sistema_Eventos_Microsservicos.servico_eventos.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<EventResponseDTO> getAllEvents() {
        return eventRepository.findAllActive().stream()
                .map(EventResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public EventResponseDTO getEventById(String id) {
        return eventRepository.findActiveById(id)
                .map(EventResponseDTO::fromEntity)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    public EventResponseDTO createEvent(EventCreateDTO dto) {
        Event newEvent = new Event();
        
        newEvent.setId(UUID.randomUUID().toString());
        newEvent.setEventName(dto.eventName()); // 
        newEvent.setEventDate(dto.eventDate()); // 
        newEvent.setDescription(dto.description()); // 
        newEvent.setDuration(dto.duration()); // 
        newEvent.setCategory(dto.category()); // 
        newEvent.setEventLocal(dto.eventLocal()); // 
        
        Event savedEvent = eventRepository.save(newEvent);
        return EventResponseDTO.fromEntity(savedEvent);
    }
}
