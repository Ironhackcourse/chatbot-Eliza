package com.chateliza.dto;

/**
 * DTO de salida: representa el JSON devuelto al frontend con la respuesta de Eliza.
 */
public class ChatResponse {

    private String response;

    // Constructor vacío requerido por Jackson
    public ChatResponse() {
    }

    public ChatResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
