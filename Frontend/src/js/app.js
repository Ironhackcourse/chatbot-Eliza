// =====================================================
// Configuración
// =====================================================
// URL absoluta de la API del backend (Spring Boot en el puerto 8080).
// IMPORTANTE: si usamos una URL relativa ("/api/chat") y el frontend se abre
// con Live Server (puerto 5500), el navegador pediría a 127.0.0.1:5500/api/chat
// (que no existe) y recibiríamos un 404. Con la URL absoluta siempre llega
// al backend, tanto desde Live Server como desde el propio backend (8080).
const API_URL = 'http://localhost:8080/api/chat';

// Elementos del DOM que vamos a utilizar
const chatForm = document.getElementById('chat-form');
const messageInput = document.getElementById('message-input');
const chatMessages = document.getElementById('chat-messages');
const typingIndicator = document.getElementById('typing-indicator');

// Añade una burbuja de mensaje al contenedor del chat
function addMessage(text, sender) {
    const messageElement = document.createElement('div');
    messageElement.classList.add('message', sender);
    messageElement.textContent = text;
    chatMessages.appendChild(messageElement);
    // Hacer scroll hacia abajo para mostrar el último mensaje
    chatMessages.scrollTop = chatMessages.scrollHeight;
}

// Muestra el indicador de "Eliza está escribiendo..."
function showTypingIndicator() {
    typingIndicator.classList.remove('hidden');
}

// Oculta el indicador de escritura
function hideTypingIndicator() {
    typingIndicator.classList.add('hidden');
}

// Espera un tiempo determinado usando una promesa + setTimeout
function wait(ms) {
    return new Promise((resolve) => setTimeout(resolve, ms));
}

// Envía el mensaje al backend y muestra la respuesta de Eliza
async function sendMessage(text) {
    // 1. Mostrar el mensaje del usuario en el chat
    addMessage(text, 'user');
    messageInput.value = '';

    // 2. Mostrar el indicador de escritura mientras Eliza "piensa"
    showTypingIndicator();

    try {
        // --- PASO A: Llamada asíncrona al backend usando async/await ---
       

        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ message: text })
        });

        // --- PASO B: Comprobar si el servidor respondió con éxito ---
        if (!response.ok) {
            // Lanza un error que salta al catch (ej. 404 o 500)
            throw new Error(`El servidor respondió con HTTP ${response.status} (${response.statusText})`);
        }

        // --- PASO C: Convertir la respuesta a JSON ---
        const data = await response.json();

        // --- PASO D: Validar que llega la respuesta esperada ---
        if (!data || !data.response) {
            throw new Error('La respuesta del servidor no contiene el campo "response"');
        }

        // --- PASO E: Esperar 1.5 s antes de ocultar el indicador y mostrar la respuesta.
        //     El setTimeout simula la latencia de escritura de Eliza.
        //     El await anterior garantiza que ya tenemos la respuesta.
        setTimeout(() => {
            hideTypingIndicator();
            addMessage(data.response, 'bot');
        }, 1500);

    } catch (error) {
        // --- CONTROL DE ERRORES: muestra el fallo en consola y en el chat ---
        console.error('[Eliza] ERROR detectado en la petición:', error);
        hideTypingIndicator();
        addMessage('⚠️ Error: ' + error.message, 'bot');
    }
}

// Configurar el envío del formulario
chatForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    const text = messageInput.value.trim();
    if (text.length > 0) {
        await sendMessage(text);
    }
});
