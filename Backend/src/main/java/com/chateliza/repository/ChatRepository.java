package com.chateliza.repository;

import com.chateliza.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad ChatMessage.
 * Spring Data genera la implementación de los métodos declarados.
 */
@Repository
public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

    /**
     * Busca el último registro más reciente de la tabla.
     * Se usa para detectar respuestas repetidas y evitar redundancia.
     */
    Optional<ChatMessage> findTopByOrderByIdDesc();
}
