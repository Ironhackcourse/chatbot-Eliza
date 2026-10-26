# Chateliza - Conversacional Assistant (Backend)

## 📋 Description

**Chateliza** é um bot assistente de chat desenvolvido com Python, focado em interações naturais e atendimento ao usuário. O sistema foi projetado para melhorar a experiência do usuário através de conversas fluidas e personalizadas.

---

## 🏗️ Stack Tech Stack

- **Backend**: Python (FasterThanUI)
- **Database**: PostgreSQL
- **Frontend**: HTML5, JavaScript

---

## 📁 Project Structure

````
/home/xavier/Documentos/IronHack/ChatEli/
├── Backend/
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/com/chateliza/ChatelizaApplication.java          # Application entry point
│       │   ├── config/
│       │   │   └── CorsConfig.java                                     # CORS configuration
│       │   ├── controller/
│       │   │   └── ChatController.java                                # Main API controller
│       │   ├── dto/
│       │   │   ├── ChatRequest.java                                   # Input request format
│       │   │   └── ChatResponse.java                                  # Output response format
│       │   ├── model/
│       │   │   └── ChatMessage.java                                   # Conversation state
│       │   └── repository/
│       │       └── ChatRepository.java                                # Data access layer
│       └── resources/
│           ├── application.properties                                 # Configuration file
│           └── schema.sql                                             # Database schema
├── Frontend/
│   └── index.html                                                 # Main HTML interface
│   └── src/
│       ├── css/
│       │   └── style.css                                            # Styling
│       └── js/
│           └── app.js                                               # Application logic
├── README.md                                                        # This file
````

---

## 🚀 Features

### Bot Skills
- **Conversational AI**: Natural language interactions and multi-turn conversations
- **Personalization**: Adapts to user preferences and context
- **Efficient Processing**: Optimized operations for real-time chat

### Core Components

#### API
- RESTful endpoints for conversational interactions
- Secure authentication and authorization
- Error handling and logging

#### Data Management
- Schema validation
- Transaction integrity
- Audit logging

---

## 📦 Installation & Setup

### Prerequisites
- Python 3.9 or higher
- PostgreSQL 14+
- Node.js 18+ (optional frontend)

### Running the Application

1. **Install dependencies:**
```bash
cd /home/xavier/Documentos/IronHack/ChatEli
pip install -r pom.xml
```

2. **Run the application:**
```bash
java -jar target/classes/com/chateliza.ChatelizaApplication.jar
```

3. **Configure CORS (if needed):**
```bash
cp target/config/CorsConfig.java .
java -jar target/classes/com/chateliza.ChatelizaApplication.jar
```

---

## 🛡️ Security Best Practices

### Database Security
- Input validation and sanitization
- SQL injection prevention with parameterized queries
- Regular database backups
- Access control mechanisms

### CORS Configuration
The project currently uses **strict CORS** settings to prevent cross-origin attacks:
```java
// CorsConfig.java example
private final String corsOriginPattern = "http://localhost:3000";
private final List<String> allowedOrigins = Arrays.asList(
    corsOriginPattern,
    "https://your-domain.com"
);
```

---

## 🧪 Testing & Deployment

### Unit Tests
Use test runners for functional and unit tests.

### Integration Testing
Run integration tests against the database schema:

```bash
java -jar target/classes/com/chateliza.ChatelizaApplication.jar
sqlite3 backend/schema.sql
# Execute the following queries to verify data integrity
-- Check user table
SELECT * FROM chat.users;

-- Check message collection
SELECT COUNT(*) as total_messages FROM chat.messages;

-- Validate schema constraints
ALTER TABLE chat.users RENAME COLUMN users_id TO conversation_id;
```

---

## 📝 Database Schema (schema.sql)

### Users Table
- `id` - Primary key
- `user_id` - Foreign key to database user tables
- `name` - User name
- `email` - Email address
- `created_at` - Timestamp of creation
- `updated_at` - Timestamp of last update
- `conversation_id` - Referenced foreign key for conversation

### Messages Table
- `id` - Primary key
- `user_id` - Foreign key to database user tables
- `user_name` - User name associated with message sender
- `subject` - Message subject (optional)
- `body` - Message body
- `timestamp` - Timestamp of creation
- `conversation_id` - Referenced foreign key

### Settings Table
- `id` - Primary key
- `enabled` - Boolean flag for settings visibility
- `name` - Setting name
- `default_value` - Default value (optional)
- `description` - Description (optional)
- `required` - Required boolean flag

---

## 🤝 Contributing

Contributions are welcome! Please follow these guidelines:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📖 License

This project is licensed under the MIT License.

---

**Created with ❤️ by Copilot SDK Assistant**
