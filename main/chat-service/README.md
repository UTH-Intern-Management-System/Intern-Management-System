# Chat Service Documentation

## Overview

The Chat Service is a microservice responsible for managing real-time messaging functionality in the Intern Management System. It provides REST APIs for creating conversations, sending messages, and retrieving chat history.

## Architecture

### Technology Stack
- **Framework**: Spring Boot 3.x
- **Database**: MongoDB
- **Security**: Spring Security with JWT
- **Port**: 8085
- **Context Path**: `/chat`

### Dependencies
- Spring Boot Starter Web
- Spring Boot Starter Security
- Spring Boot Starter Data MongoDB
- Spring Cloud OpenFeign (for service communication)
- Lombok
- Validation API

## API Endpoints

### Base URL
```
http://localhost:8085/chat
```

### Authentication
All endpoints require JWT authentication via `Authorization: Bearer <token>` header.

### 1. Conversation Management

#### Create Conversation
```http
POST /conversations/create
Content-Type: application/json
Authorization: Bearer <token>

{
  "type": "DIRECT",
  "participantIds": ["user1", "user2"]
}
```

**Response:**
```json
{
  "code": 1000,
  "message": "Success",
  "result": {
    "id": "conv_123",
    "type": "DIRECT",
    "participantsHash": "hash_value",
    "conversationAvatar": "avatar_url",
    "conversationName": "Direct Chat",
    "participants": [
      {
        "userId": "user1",
        "username": "john_doe",
        "firstName": "John",
        "lastName": "Doe",
        "avatar": "avatar1.jpg"
      }
    ],
    "createdDate": "2024-01-01T10:00:00Z",
    "modifiedDate": "2024-01-01T10:00:00Z"
  }
}
```

#### Get My Conversations
```http
GET /conversations/my-conversations
Authorization: Bearer <token>
```

**Response:**
```json
{
  "code": 1000,
  "message": "Success",
  "result": [
    {
      "id": "conv_123",
      "type": "DIRECT",
      "participantsHash": "hash_value",
      "conversationAvatar": "avatar_url",
      "conversationName": "Direct Chat",
      "participants": [...],
      "createdDate": "2024-01-01T10:00:00Z",
      "modifiedDate": "2024-01-01T10:00:00Z"
    }
  ]
}
```

### 2. Message Management

#### Send Message
```http
POST /messages/create
Content-Type: application/json
Authorization: Bearer <token>

{
  "conversationId": "conv_123",
  "message": "Hello, how are you?"
}
```

**Response:**
```json
{
  "code": 1000,
  "message": "Success",
  "result": {
    "id": "msg_456",
    "conversationId": "conv_123",
    "me": true,
    "message": "Hello, how are you?",
    "sender": {
      "userId": "user1",
      "username": "john_doe",
      "firstName": "John",
      "lastName": "Doe",
      "avatar": "avatar1.jpg"
    },
    "createdDate": "2024-01-01T10:05:00Z"
  }
}
```

#### Get Messages
```http
GET /messages?conversationId=conv_123
Authorization: Bearer <token>
```

**Response:**
```json
{
  "code": 1000,
  "message": "Success",
  "result": [
    {
      "id": "msg_456",
      "conversationId": "conv_123",
      "me": true,
      "message": "Hello, how are you?",
      "sender": {
        "userId": "user1",
        "username": "john_doe",
        "firstName": "John",
        "lastName": "Doe",
        "avatar": "avatar1.jpg"
      },
      "createdDate": "2024-01-01T10:05:00Z"
    }
  ]
}
```

## Data Models

### ChatMessage
```java
{
  "id": "String",
  "conversationId": "String",
  "message": "String",
  "sender": "ParticipantInfo",
  "createdDate": "Instant"
}
```

### Conversation
```java
{
  "id": "String",
  "type": "String (DIRECT/GROUP)",
  "participantsHash": "String",
  "conversationAvatar": "String",
  "conversationName": "String",
  "participants": "List<ParticipantInfo>",
  "createdDate": "Instant",
  "modifiedDate": "Instant"
}
```

### ParticipantInfo
```java
{
  "userId": "String",
  "username": "String",
  "firstName": "String",
  "lastName": "String",
  "avatar": "String"
}
```

## Configuration

### Application Properties
```yaml
server:
  port: 8085
  servlet:
    context-path: /chat

spring:
  application:
    name: chat-service
  data:
    mongodb:
      uri: mongodb://root:root@localhost:27017/chat-service?authSource=admin
      auto-index-creation: true

app:
  services:
    profile:
      url: http://localhost:8081/profile
```

### Security Configuration
- JWT-based authentication
- All endpoints require authentication except public endpoints (currently none)
- Custom JWT decoder for token validation
- OAuth2 Resource Server configuration

## Service Dependencies

### Profile Service Integration
The chat service integrates with the Profile Service to:
- Fetch user information when creating messages
- Validate participant information
- Get user details for message display

**Profile Service Client:**
```java
@FeignClient(name = "profile-service", url = "${app.services.profile.url}")
public interface ProfileClient {
    @GetMapping("/users/{userId}")
    ApiResponse<UserResponse> getProfile(@PathVariable String userId);
}
```

## Error Handling

### Error Codes
- `CONVERSATION_NOT_FOUND` (404): Conversation doesn't exist or user not authorized
- `UNAUTHENTICATED` (401): Invalid or missing JWT token
- `UNCATEGORIZED_EXCEPTION` (500): General server error

### Error Response Format
```json
{
  "code": 1001,
  "message": "Conversation not found",
  "result": null
}
```

## Database Schema

### Collections

#### conversations
```javascript
{
  "_id": "ObjectId",
  "type": "String",
  "participantsHash": "String",
  "conversationAvatar": "String",
  "conversationName": "String",
  "participants": [
    {
      "userId": "String",
      "username": "String",
      "firstName": "String",
      "lastName": "String",
      "avatar": "String"
    }
  ],
  "createdDate": "Date",
  "modifiedDate": "Date"
}
```

#### chat_messages
```javascript
{
  "_id": "ObjectId",
  "conversationId": "String",
  "message": "String",
  "sender": {
    "userId": "String",
    "username": "String",
    "firstName": "String",
    "lastName": "String",
    "avatar": "String"
  },
  "createdDate": "Date"
}
```

## API Gateway Integration

The chat service is integrated with the API Gateway:

### Gateway Route Configuration
```yaml
- id: chat_service
  uri: http://localhost:8085
  predicates:
    - Path=${app.api-prefix}/chat/**
  filters:
    - StripPrefix=2
```

### Public API Access
```
http://localhost:8888/api/v1/chat/conversations/create
http://localhost:8888/api/v1/chat/conversations/my-conversations
http://localhost:8888/api/v1/chat/messages/create
http://localhost:8888/api/v1/chat/messages?conversationId=...
```

## Frontend Integration

### Service Implementation
```javascript
// chatService.js
import api from './api';

const chatService = {
  createConversation: async ({ type, participantIds }) => {
    const response = await api.post('/chat/conversations/create', { type, participantIds });
    return response.data;
  },
  
  myConversations: async () => {
    const response = await api.get('/chat/conversations/my-conversations');
    return response.data;
  },
  
  getMessages: async (conversationId) => {
    const response = await api.get('/chat/messages', { params: { conversationId } });
    return response.data;
  },
  
  sendMessage: async ({ conversationId, message }) => {
    const response = await api.post('/chat/messages/create', { conversationId, message });
    return response.data;
  }
};
```

### Authentication Setup
```javascript
// api.js
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('ims_token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});
```

## Development Setup

### Prerequisites
- Java 17+
- MongoDB 4.4+
- Maven 3.6+

### Running the Service
```bash
cd chat-service
./mvnw spring-boot:run
```

### Testing
```bash
./mvnw test
```

## Monitoring and Logging

### Health Check
```http
GET /actuator/health
```

### Logs
- Application logs: `logs/chat-service.log`
- Log level: INFO (configurable via `application.yaml`)

## Future Enhancements

### Planned Features
1. **WebSocket Support**: Real-time messaging via WebSocket/STOMP
2. **Message Types**: Support for file attachments, images, emojis
3. **Message Status**: Read receipts, delivery status
4. **Push Notifications**: Integration with notification service
5. **Message Search**: Full-text search capabilities
6. **Message Encryption**: End-to-end encryption for sensitive conversations

### Performance Optimizations
1. **Message Pagination**: Implement cursor-based pagination for large conversations
2. **Caching**: Redis integration for frequently accessed data
3. **Message Queuing**: Async message processing for high throughput
4. **Database Indexing**: Optimize MongoDB indexes for better query performance

## Troubleshooting

### Common Issues

1. **Authentication Errors**
   - Verify JWT token is valid and not expired
   - Check token format: `Bearer <token>`
   - Ensure user exists in identity service

2. **Database Connection Issues**
   - Verify MongoDB is running on localhost:27017
   - Check database credentials in application.yaml
   - Ensure database exists and user has proper permissions

3. **Profile Service Integration**
   - Verify profile service is running on port 8081
   - Check network connectivity between services
   - Verify user exists in profile service

### Debug Mode
Enable debug logging by adding to `application.yaml`:
```yaml
logging:
  level:
    com.devteria.chat: DEBUG
```

## Support

For technical support or questions about the chat service, please contact the development team or create an issue in the project repository.