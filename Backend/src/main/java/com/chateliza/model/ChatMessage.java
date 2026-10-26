package com.chateliza.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

/**
 * Entidad JPA que representa un mensaje de la conversación con Eliza.
 * Se mapea a la tabla 'chat_history' de la base de datos.
 */
@Entity
@Table(name = "chat_history")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mensaje enviado por el usuario
    @Column(name = "user_message", nullable = false, length = 1000)
    private String userMessage;

    // Respuesta generada por Eliza
    @Column(name = "bot_response", nullable = false, length = 1000)
    private String botResponse;

    // Fecha y hora del mensaje
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    // Constructor vacío requerido por JPA
    public ChatMessage() {
    }

    public ChatMessage(String userMessage, String botResponse) {
        this.userMessage = userMessage;
        this.botResponse = botResponse;
        this.timestamp = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getBotResponse() {
        return botResponse;
    }

    public void setBotResponse(String botResponse) {
        this.botResponse = botResponse;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
