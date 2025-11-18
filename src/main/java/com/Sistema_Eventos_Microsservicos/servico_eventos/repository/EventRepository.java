package com.Sistema_Eventos_Microsservicos.servico_eventos.repository;

import com.Sistema_Eventos_Microsservicos.servico_eventos.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repositório Spring Data JPA para a entidade {@link Event}.
 * <p>
 * Fornece métodos CRUD básicos e consultas personalizadas que respeitam
 * a lógica de "soft-delete" (exclusão suave), buscando apenas os registros
 * onde {@code deletedAt} é {@code NULL}.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    /**
     * Busca um evento *ativo* (deleted_at = false) pelo seu ID.
     *
     * @param id O ID (UUID) do evento a ser buscado.
     * @return um {@link Optional} contendo o {@link Event} se encontrado e ativo,
     * ou {@link Optional#empty()} se não for encontrado ou se estiver deletado.
     */
    @Query("SELECT e FROM Event e WHERE e.id = ?1 AND e.deletedAt IS NULL")
    Optional<Event> findActiveById(String id);

    /**
     * Retorna uma lista de todos os eventos *ativos* (deleted_at = false).
     *
     * @return uma {@link List} de {@link Event} ativos ou uma {@link List<>} vazia se não for encontrado nada.
     */
    @Query("SELECT e FROM Event e WHERE e.deletedAt IS NULL")
    List<Event> findAllActive();
}