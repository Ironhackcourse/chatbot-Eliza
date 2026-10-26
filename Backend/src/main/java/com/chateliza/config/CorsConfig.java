package com.chateliza.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración global de CORS.
 *
 * Permite que el frontend, servido desde un origen distinto al del backend
 * (por ejemplo, Live Server en el puerto 5500), pueda hacer peticiones
 * al backend que corre en el puerto 8080. Esos contextos son "cross-origin"
 * y el navegador bloquea las peticiones salvo que el servidor las autorice.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")                        // Aplica a todos los endpoints
                .allowedOrigins("*")                       // Cualquier origen (desarrollo)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")                       // Cualquier cabecera (ej. Content-Type)
                .maxAge(3600);                             // Cachear el preflight 1h
    }
}
