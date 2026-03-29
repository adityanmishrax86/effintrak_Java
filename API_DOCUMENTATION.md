# EffinTrak API Contract

## Source of Truth
- OpenAPI JSON: `GET /v3/api-docs`
- OpenAPI YAML: `GET /v3/api-docs.yaml`
- Swagger UI: `GET /swagger-ui.html`

These are generated from Spring controllers and annotations at runtime.

## Authentication Contract
- Scheme: `Bearer` JWT in `Authorization` header.
- Public endpoints:
  - `POST /api/v1/users/register`
  - `POST /api/v1/users/login`
  - `POST /api/v1/users/refresh`
  - `GET /v3/api-docs` and `GET /v3/api-docs.yaml`
  - `GET /swagger-ui.html`
  - `GET /actuator/health`, `GET /actuator/info`, `GET /actuator/prometheus`

## Key Contracts
- AI Chat:
  - `POST /api/chat/prompt`
  - `POST /api/chat/prompt/simple`
  - `GET /api/chat/conversations`
  - `GET /api/chat/conversations/paginated`
  - `GET /api/chat/conversations/{conversationId}`
  - `PUT /api/chat/conversations/{conversationId}`
  - `DELETE /api/chat/conversations/{conversationId}`
- User settings:
  - `GET /api/v1/user-settings/me`
  - `PUT /api/v1/user-settings/me`

## Contract Export (CI-Friendly)
Run the app, then export:

```bash
curl -s http://localhost:8080/v3/api-docs.yaml > openapi.yaml
```

Commit `openapi.yaml` from CI if you want versioned contract snapshots.
