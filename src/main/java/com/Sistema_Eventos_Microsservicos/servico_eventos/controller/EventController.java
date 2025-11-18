package com.Sistema_Eventos_Microsservicos.servico_eventos.controller;

import com.Sistema_Eventos_Microsservicos.servico_eventos.dto.EventCreateDTO;
import com.Sistema_Eventos_Microsservicos.servico_eventos.dto.EventResponseDTO;
import com.Sistema_Eventos_Microsservicos.servico_eventos.service.EventService;
import com.Sistema_Eventos_Microsservicos.servico_eventos.model.Event;
import com.Sistema_Eventos_Microsservicos.servico_eventos.exception.EventNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para as operações de CRUD (Criar, Ler, Atualizar, Deletar) da entidade {@link Event}.
 * <p>
 * Todos os endpoints deste controlador são protegidos e esperam que o API Gateway
 * injete os headers de segurança (`X-User-Id`, `X-User-Roles`) após a
 * validação do token JWT.
 */
@RestController
@RequestMapping("/events") // Endpoint em inglês
public class EventController {
    @Autowired
    private EventService eventService;

    /**
     * Retorna uma lista de todos os eventos disponíveis.
     * Este endpoint é acessível a qualquer usuário logado.
     *
     * @return Um {@link ResponseEntity} com status {@code 200 OK} e a lista
     * de {@link EventResponseDTO}.
     */
    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    /**
     * Retorna um evento específico pelo seu ID.
     * Este endpoint é acessível a qualquer usuário logado.
     *
     * @param id O ID (UUID) do evento a ser buscado (da URL).
     * @return Um {@link ResponseEntity} com status {@code 200 OK} e o
     * {@link EventResponseDTO} encontrado.
     * @throws EventNotFoundException (Tratado pelo GlobalExceptionHandler)
     * se o evento não for encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable String id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    /**
     * Cria um novo evento no sistema.
     * O usuário logado que faz a requisição será definido como o "criador" do evento.
     *
     * @param requesterId O ID do usuário (injetado pelo Gateway via Header "X-User-Id"),
     * usado para definir o criador.
     * @param dto O DTO {@link EventCreateDTO} com os dados do novo evento.
     * @return Um {@link ResponseEntity} com status {@code 201 Created} e o
     * {@link EventResponseDTO} do evento recém-criado.
     */
    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(
            @RequestHeader("X-User-Roles") String requesterRoles,
            @RequestHeader("X-User-Id") String requesterId,
            @Valid @RequestBody EventCreateDTO dto
    ) {
        EventResponseDTO newEvent = eventService.createEvent(dto, requesterId, requesterRoles);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEvent);
    }
}