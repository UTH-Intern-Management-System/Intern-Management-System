### Chat Service - Deployment Diagram

```mermaid
flowchart LR
  subgraph Client
    FE[Frontend (React)]
  end

  subgraph Infra[Infrastructure]
    GW[API Gateway (Spring Cloud Gateway)]
    CS[Chat Service (Spring Boot)]
    PR[Profile Service]
    ID[Identity Service]
    MDB[(MongoDB)]
  end

  FE -- HTTPS --> GW
  GW -- HTTP --> CS
  GW -- HTTP --> PR
  GW -- HTTP --> ID
  CS -- HTTP --> PR
  CS -- Driver --> MDB
```

