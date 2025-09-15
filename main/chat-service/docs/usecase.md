### Chat Service - Use Case Diagram

```mermaid
usecase
  title Chat Service Use Cases

  actor User as "Mentor/Intern"
  actor Identity as "Identity Service"
  actor Gateway as "API Gateway"

  rectangle ChatService {
    (Create Conversation) as UC_CreateConv
    (View My Conversations) as UC_ListConvs
    (Send Message) as UC_SendMsg
    (Get Messages) as UC_GetMsgs
  }

  User -->> Gateway : Uses REST APIs
  Gateway -->> ChatService : Routes Authenticated Requests
  Gateway -->> Identity : Introspect Token

  User -- UC_CreateConv
  User -- UC_ListConvs
  User -- UC_SendMsg
  User -- UC_GetMsgs

  UC_SendMsg .> (Fetch Sender Profile) : <<include>>
```


