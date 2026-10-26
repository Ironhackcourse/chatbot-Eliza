package com.chateliza.controller;

import com.chateliza.dto.ChatRequest;
import com.chateliza.dto.ChatResponse;
import com.chateliza.service.ElizaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST que expone el endpoint de chat de Eliza.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/chat")
public class ChatController {

    private final ElizaService elizaService;

    public ChatController(ElizaService elizaService) {
        this.elizaService = elizaService;
    }

    /**
     * Recibe el mensaje del usuario y devuelve la respuesta de Eliza.
     */
    @PostMapping
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        String respuesta = elizaService.processMessage(request.getMessage());
        return ResponseEntity.ok(new ChatResponse(respuesta));
    }
}
