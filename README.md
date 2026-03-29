# EffinTrak Backend

Spring Boot backend for personal finance tracking with AI-powered natural language actions.

## Core Capabilities
- Expense and income tracking
- Budgets, savings goals, subscriptions, credits, transfers, recurring transactions
- Reporting and analytics
- JWT auth with refresh tokens
- AI chat that maps natural language to finance operations with guardrails

## AI Chat Highlights
- Endpoint: `POST /api/chat/prompt`
- Supports all configured operations in `app.ai.enabled-operations`
- Supports multi-additions in one prompt for expense/income
  - Example: "Add $20 coffee and $50 groceries from my main account"
- Supports query-style analytics via `QUERY_FINANCIAL_DATA`
  - Summary, spending by category, trends, report range, search, top categories

## Observability
- Actuator + Prometheus: `/actuator/health`, `/actuator/info`, `/actuator/prometheus`
- AI execution metrics via `AIMetricsRecorder`
- Correlation ID filter for request tracing

## Run
```bash
./mvnw spring-boot:run
```

## Test
```bash
# Full test suite except key-dependent Spring AI integration test
./mvnw -Dtest='!ChatControllerIntegrationTest' test

# AI-focused unit suite
./mvnw -Dtest=FinanceToolsTest,ChatServiceTest,PromptTemplateServiceTest,AIResponseValidatorTest,AIModelManagerTest,AIToolPolicyTest,AIContextServiceTest test
```

## Documentation Index
- `API_DOCUMENTATION.md` - API contract and OpenAPI source of truth
- `NATURAL_LANGUAGE_CHAT_GUIDE.md` - AI chat features, operations, prompt patterns
- `DOCKER_DEPLOYMENT.md` - Docker and Kubernetes deployment
- `TESTING_GUIDE.md` - Test strategy and commands
- `SERVICE_FLOW.md` - Service-level architecture/flow overview
