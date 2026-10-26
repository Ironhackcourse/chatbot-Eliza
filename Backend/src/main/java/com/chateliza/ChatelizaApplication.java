package com.chateliza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot.
 * Marca la ubicación base donde se escanean los componentes de la app.
 */
@SpringBootApplication
public class ChatelizaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatelizaApplication.class, args);
    }
}
