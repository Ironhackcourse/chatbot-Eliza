package com.chateliza.dto;

/**
 * DTO de entrada: representa el JSON recibido del frontend con el mensaje del usuario.
 */
public class ChatRequest {

    private String message;

    // Constructor vacío requerido por Jackson para deserializar JSON
    public ChatRequest() {
    }

    public ChatRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
