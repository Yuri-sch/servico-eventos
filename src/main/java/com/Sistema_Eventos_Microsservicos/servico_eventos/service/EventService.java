package com.Sistema_Eventos_Microsservicos.servico_eventos.service;

import com.Sistema_Eventos_Microsservicos.servico_eventos.dto.EventCreateDTO;
import com.Sistema_Eventos_Microsservicos.servico_eventos.dto.EventResponseDTO;
import com.Sistema_Eventos_Microsservicos.servico_eventos.exception.EventNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import com.Sistema_Eventos_Microsservicos.servico_eventos.model.Event;
import com.Sistema_Eventos_Microsservicos.servico_eventos.repository.EventRepository;
import com.Sistema_Eventos_Microsservicos.servico_eventos.security.AuthorizationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Camada de serviço que encapsula a lógica de negócios para Eventos.
 * <p>
 * Responsável por criar, recuperar e validar dados de eventos,
 * bem como verificar as permissões de acesso.
 */
@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private AuthorizationHelper authorizationHelper;

    /**
     * Retorna uma lista de todos os eventos ativos.
     * Este endpoint é acessível a qualquer usuário autenticado.
     *
     * @return Uma {@link List} de {@link EventResponseDTO} representando todos os eventos ativos.
     */
    public List<EventResponseDTO> getAllEvents() {
        return eventRepository.findAllActive().stream()
                .map(EventResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Busca um evento ativo específico pelo seu ID.
     * Este endpoint é acessível a qualquer usuário autenticado.
     *
     * @param id O ID (UUID) do evento a ser buscado.
     * @return O {@link EventResponseDTO} do evento encontrado.
     * @throws EventNotFoundException Se nenhum evento ativo com o ID fornecido for encontrado.
     */
    public EventResponseDTO getEventById(String id) {
        return eventRepository.findActiveById(id)
                .map(EventResponseDTO::fromEntity)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    /**
     * Cria um novo evento no sistema.
     * Esta operação é restrita e exige que o usuário tenha a role {@code ROLE_ADMIN}.
     *
     * @param dto O DTO {@link EventCreateDTO} contendo os dados do novo evento.
     * @param requesterId O ID do usuário que está fazendo a solicitação (atualmente não utilizado
     * nesta implementação, mas recebido do controller).
     * @param requesterRoles As roles do usuário (ex: "ROLE_ADMIN"), usadas para verificação de
     * permissão.
     * @return O {@link EventResponseDTO} do evento recém-criado e salvo.
     * @throws AccessDeniedException Se o {@code requesterRoles} não contiver a permissão de ADMIN.
     */
    public EventResponseDTO createEvent(EventCreateDTO dto, String requesterId, String requesterRoles) {
        authorizationHelper.checkIsAdmin(requesterRoles);
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
