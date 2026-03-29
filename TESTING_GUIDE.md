# Testing Guide

## Prerequisites
- Java 21+
- Docker running (for Testcontainers-backed integration tests)

## Commands

### 1) Compile check
```bash
./mvnw -DskipTests compile
```

### 2) Full suite excluding key-dependent Spring AI integration test
```bash
./mvnw -Dtest='!ChatControllerIntegrationTest' test
```

### 3) AI-focused test suite
```bash
./mvnw -Dtest=FinanceToolsTest,ChatServiceTest,PromptTemplateServiceTest,AIResponseValidatorTest,AIModelManagerTest,AIToolPolicyTest,AIContextServiceTest test
```

## AI Test Coverage Scope
- `FinanceToolsTest`: all AI tool feature paths (expense, income, budgets, savings, subscriptions, credits, transfers, recurring, reporting/query)
- `ChatServiceTest`: request validation, rate limiting, batch-detection behavior
- `PromptTemplateServiceTest`: prompt profile loading and required templates
- `AIResponseValidatorTest`: validation and operation intent checks
- `AIModelManagerTest`: model selection and fallback
- `AIToolPolicyTest`: operation allowlist policy behavior
- `AIContextServiceTest`: user-context assembly

## Notes
- If Docker is unavailable, container-backed integration tests may be skipped/fail depending on test configuration.
- `ChatControllerIntegrationTest` requires live model provider credentials.
