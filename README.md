# ChatEli - Sistema de IA en Conversación AI

## 📋 Resumen del Proyecto

Este es un proyecto de desarrollo completo con dos pilares: **Backend** y **Frontend**. El sistema se centra en una aplicación de chat interactiva basada en IA, similar a la llamada "Eliza".

---

## 🏗️ Arquitectura del Proyecto

### Backend
- **Tecnologías**: Java Spring Boot
- **Materiales**: Maven
- **Base de Datos**: MariaDB (connector oficial)
- **Entidad JPA**: `ChatMessage` con tabla `chat_history`

### Frontend
- **Tecnologías**: HTML5, CSS3, JavaScript ES6+
- **Librerías**: jQuery para la interacción básica del formulario

---

## 🗂️ Estructura de Archivos

### Backend (src/main/java/com/chateliza/)
```
com/chateliza/
├── ChatelizaApplication.java          # Clase principal y configuración SpringApplication
├── controller/
│   ├── ChatController.java            # Interfaz REST para manejar la API
├── dto/
│   ├── ChatRequest.java               # Entidad de entrada (DTO)
│   └── ChatResponse.java              # Entidad de salida (DTO)
├── model/
│   └── ChatMessage.java               # Base de datos de respuesta y mensaje del usuario
├── repository/
│   └── ChatRepository.java            # Repository JPA
├── service/
│   ├── ElizaService.java              # Lógica de negocio principal
│   └── normalizeInput.java            # Normalización del mensaje (input)
├── resources/
│   ├── application.properties         # Configuración del Spring Boot
│   └── schema.sql                     # Esquema para la base de datos
└── generated-sources/
    └── annotations/
        └── com/chateliza/           # Anotaciones compiladas
```

### Frontend (Frontend/src/)
```
index.html                          # Estructura HTML del frontend
src/
├── css/
│   ├── style.css                    # Estilos CSS con variables de color
├── js/
│   └── app.js                       # Lógica JavaScript principal
```

---

## 🛠️ Funcionalidades Principales

### Chat Interface
- ✅ Menú de navegación
- ✅ Inputs de texto para ambos lados
- ✅ Mensajes visibles en tiempo real
- ✅ Indicador visual de escritura
- ✅ Validación del mensaje anterior
- ✅ Respuestas inteligentes basadas en reglas

### Lógica de IA (Eliza)
1. **Validación básica**: Respuesta genérica si el mensaje es muy corto (< 3 caracteres)
2. **Normalización**: Texto minúsculo y sin acentos
3. **Motor de reglas simple**: Condicionales basados en palabras clave
4. **Estrategias de negocio**:
   - Saludo: "¡Hola! Soy Eliza..."
   - Pregunta ("necesito", "quiero"): "Me alegra que quieras ayudarme"
   - Dudas ("creo que..."): "Entiendo, ¿cuál es tu duda?"
   - Relación familiar: "Entendido, cuéntame más sobre eso"
   - Confirmación/seguridad: "¿No estás seguro? Vamos a pensarlo juntos"
5. **Persistencia**: Respuestas y mensajes guardados en la base de datos
6. **Evitar repeticiones**: Si se genera una respuesta ya, añadir "Como te decía, ..."

---

## 🚀 Características Técnicas

### Optimizaciones
- **Diseño Responsive**: Menú móvil y barra superior fija
- **CSS Grid/Flexbox**: Diseño limpio y organizado
- **Variables CSS**: Consistencia de colores en el sistema

### Mejoras Anticipadas (para el próximo ciclo)
- Implementación más completa del motor de reglas de IA
- API real con backend funcional
- Optimización de carga para móviles
- Persistencia avanzada de usuarios
- Sistema de persistencia de respuestas recurrentes
- Feedback visual mejorado

---

## 📁 Arquivos Importantes

### Configuración del Proyecto
- **Backend**: `pom.xml` (Maven), `src/main/java/...`, `src/main/resources/application.properties`
- **Frontend**: `index.html` (HTML5), `src/css/style.css`, `src/js/app.js`

### Base de Datos
- Schema en `resources/schema.sql`

### API Endpoint
- `POST /api/chat` - Recibe mensaje del usuario y devuelve respuesta de Eliza

---

## 🔄 Ciclo de Trabajo

### Frontend → Backend → Database
1. **Frontend**: Recebe texto, valida con regex simple
2. **Backend**: Enrolla en el motor de reglas de IA
3. **Database**: Guarda el mensaje y respuesta en `chat_history`
4. **Frontend**: Visualiza resultados del backend

### Loop Recurrente
Si el usuario deja la respuesta ya (como "Como te decía, ..."), se guarda automáticamente. El sistema genera respuestas recurrentes basadas en las reglas implementadas.

---

## 💡 Uso y Personalización

1. **Editar estilos**: Cambia los colores en `src/css/style.css`
2. **Ajustar comportamiento**: Modifica el motor de reglas o la lógica en `ElizaService.java`
3. **Manejar errores**: Implementa try-catch y manejo mejorado
4. **Añadir funcionalidades**: Creación de nuevos endpoints o funciones

---

## 📝 Estructura del Frontend (HTML)

```html
<header class="chat-header">🤖 Eliza</header>

<main id="chat-messages" class="chat-messages"></main>

<div id="typing-indicator" class="typing-indicator hidden">
    <span class="typing-text">Eliza está escribiendo</span>
    <span class="dot">.</span>
    <span class="dot">.</span>
    <span class="dot">.</span>
</div>

<form id="chat-form" class="chat-input-form">
    <input type="text" id="message-input" placeholder="Escribe tu mensaje..." autocomplete="off"/>
    <button type="submit" id="send-button">Enviar</button>
</form>
```

---

## 📦 Requerimientos

- **Java**: JDK 8+, Spring Boot, MariaDB JDBC driver
- **Frontend**: Node.js, jQuery para interacción básica
- **Base de datos**: MariaDB (3306 port)
- **Backend API**: REST JSON

---

## 🔧 Tecnologías Utilizadas

### Backend
- Spring Boot Framework
- JPA/Hibernate con MariaDB
- DTOs/Annotations
- Repositories

### Frontend
- HTML5, CSS3
- Vanilla JavaScript (ES6+)
- jQuery de base para formatters

### Estilos
- CSS Grid y Flexbox
- Variables CSS (`:root`)
- Animaciones con CSS Keyframes

---

## 📚 Referencias

Esta documentación fue creada siguiendo las directrices que he proporcionado en el sistema. El código sigue mejores prácticas de desarrollo limpio y modularizado.

---

**Nota**: Esta es una versión básica con motor de reglas simple. Para un sistema más avanzado, se recomienda implementar:
- Motor de aprendizaje automático real
- Sistema de autenticación avanzada
- Base de datos relacionada
- Sistema de persistencia mejorado
- Integración con herramientas de IA existentes
