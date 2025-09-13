### Chat Service - ER Diagram (MongoDB)

```mermaid
erDiagram
  CONVERSATION {
    string id PK
    string type
    string participantsHash
    string conversationAvatar
    string conversationName
    date createdDate
    date modifiedDate
  }

  PARTICIPANT_INFO {
    string userId
    string username
    string firstName
    string lastName
    string avatar
  }

  CHAT_MESSAGE {
    string id PK
    string conversationId FK
    string message
    date createdDate
    boolean me (computed)
  }

  CONVERSATION ||--o{ PARTICIPANT_INFO : has
  CONVERSATION ||--o{ CHAT_MESSAGE : contains
  CHAT_MESSAGE }o--|| PARTICIPANT_INFO : sender
```

