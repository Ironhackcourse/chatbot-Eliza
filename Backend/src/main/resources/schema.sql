-- =====================================================
-- Script de inicialización de la base de datos
-- Crea la base de datos y la tabla de historial
-- =====================================================

-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS eliza_db;

-- Usar la base de datos creada
USE eliza_db;

-- Crear la tabla de historial de conversaciones con Eliza
CREATE TABLE IF NOT EXISTS chat_history (
    id INT NOT NULL AUTO_INCREMENT,
    user_message VARCHAR(1000) NOT NULL,
    bot_response VARCHAR(1000) NOT NULL,
    timestamp DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
