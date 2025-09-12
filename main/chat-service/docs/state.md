### Chat Service - State Diagram (Conversation)

```mermaid
stateDiagram-v2
  [*] --> Created
  Created --> Active: First message sent
  Active --> Active: New message
  Active --> Archived: Manual archive
  Archived --> Active: Unarchive
  Archived --> [*]
```
