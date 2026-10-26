package com.chateliza.service;

import com.chateliza.model.ChatMessage;
import com.chateliza.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Optional;

/**
 * Servicio que contiene la lógica de negocio de Eliza.
 * Orquesta: validación -> normalización -> reglas -> evitar repeticiones -> persistencia.
 */
@Service
public class ElizaService {

    private final ChatRepository chatRepository;

    // Inyección de dependencias por constructor (Spring se encarga de inyectar el repositorio)
    public ElizaService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    /**
     * Procesa el mensaje del usuario y devuelve la respuesta de Eliza.
     * <p>
     * Pasos que aplica, en orden:
     * 1. Si el mensaje es muy corto (<= 3 caracteres), retorna una respuesta genérica.
     * 2. Normaliza el texto (minúsculas y sin tildes).
     * 3. Ejecuta el motor de reglas para obtener una respuesta.
     * 4. Evita repeticiones consultando el último registro guardado.
     * 5. Persiste el mensaje antes de retornarlo.
     */
    public String processMessage(String input) {
        // Paso 1: Mensajes muy cortos -> pedir más detalle
        if (input == null || input.trim().length() <= 3) {
            return "¿Podrías detallarlo un poco más?";
        }

        // Paso 2: Normalizar el mensaje (minúsculas y sin tildes)
        String normalized = normalize(input);

        // Paso 3: Ejecutar el motor de reglas
        String response = getResponse(normalized);

        // Paso 4: Evitar repeticiones: si la respuesta es igual a la última, anteponer "Como te decía, "
        Optional<ChatMessage> lastMessage = chatRepository.findTopByOrderByIdDesc();
        if (lastMessage.isPresent() && lastMessage.get().getBotResponse().equals(response)) {
            response = "Como te decía, " + response;
        }

        // Paso 5: Persistir el mensaje y la respuesta en la base de datos
        chatRepository.save(new ChatMessage(input, response));

        // Paso 6: Retornar la respuesta para el controlador
        return response;
    }

    /**
     * Motor de reglas simple basado en condicionales.
     * Recibe el texto ya normalizado y devuelve la respuesta asociada.
     */
    private String getResponse(String normalized) {
        // Saludo
        if (containsWord(normalized, "hola")) {
            return "¡Hola! Soy Eliza. Cuéntame, ¿en qué puedo ayudarte hoy?";
        }

        // Pregunta por el motivo de la petición ("necesito" / "quiero")
        if (containsWord(normalized, "necesito", "quiero")) {
            return "Me alegra que quieras ayudarme. ¿Por qué lo necesitas?";
        }

        // Dudas o ideas ("creo que ...")
        if (normalized.contains("creo que")) {
            return "Entiendo, ¿cuál es tu duda?";
        }

        // Relación familiar
        if (containsWord(normalized, "madre", "padre")) {
            return "Entendido, cuéntame más sobre eso.";
        }

        // Seguridad / confirmación ("si", "no", "no se")
        if (normalized.contains("no se") || containsWord(normalized, "si", "no")) {
            return "¿No estás seguro? Vamos a pensarlo juntos.";
        }

        // Respuesta genérica
        return "Interesante. Cuéntame un poco más para poder ayudarte mejor.";
    }

    /**
     * Comprueba si la frase normalizada contiene alguna de las palabras dadas.
     * Compara por separación de tokens (evita coincidencias parciales, ej. "si" dentro de "siempre").
     */
    private boolean containsWord(String normalized, String... words) {
        String[] tokens = normalized.split("\\s+");
        for (String token : tokens) {
            for (String word : words) {
                if (token.equals(word)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Normaliza el mensaje: convierte a minúsculas y elimina tildes usando Normalizer + regex.
     */
    private String normalize(String input) {
        String lower = input.trim().toLowerCase();
        String withoutAccents = Normalizer.normalize(lower, Normalizer.Form.NFD)
                .replaceAll("[\\u0300-\\u036f]", "");
        // Elimina puntuación y deja solo letras/números y espacios
        return withoutAccents.replaceAll("[^a-z0-9\\s]", "").trim();
    }
}
