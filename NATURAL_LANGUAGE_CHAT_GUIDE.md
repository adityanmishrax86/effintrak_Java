# Natural Language Chat Guide

## Endpoint
- `POST /api/chat/prompt`
- `POST /api/chat/prompt/simple`

`/api/chat/prompt` request body:
```json
{
  "prompt": "Add $20 coffee and $50 groceries",
  "conversationId": "conv-123",
  "model": "llama-3.3-70b-versatile"
}
```

Authentication: bearer token required.

## Supported AI Operations
- `ADD_EXPENSE`
- `ADD_INCOME`
- `UPDATE_EXPENSE`
- `DELETE_EXPENSE`
- `GET_MONTHLY_SPENDING`
- `GET_MONTHLY_INCOME`
- `GET_FINANCIAL_SUMMARY`
- `GET_SPENDING_BY_CATEGORY`
- `CREATE_SAVINGS_GOAL`
- `GET_SAVINGS_PROGRESS`
- `ADD_TO_SAVINGS`
- `WITHDRAW_FROM_SAVINGS`
- `UPDATE_BUDGET`
- `ADD_SUBSCRIPTION`
- `GET_ACTIVE_SUBSCRIPTIONS`
- `CANCEL_SUBSCRIPTION`
- `ADD_CREDIT`
- `GET_ACTIVE_CREDITS`
- `MAKE_CREDIT_PAYMENT`
- `TRANSFER_MONEY`
- `CREATE_RECURRING_TRANSACTION`
- `GET_ACTIVE_RECURRING_TRANSACTIONS`
- `PAUSE_RECURRING_TRANSACTION`
- `DELETE_RECURRING_TRANSACTION`
- `QUERY_FINANCIAL_DATA`

## Multi-additions
AI chat supports multiple entries in one message for expense/income.

Examples:
- "Add $25 lunch and $60 groceries from account 2"
- "Record income $2000 salary and $300 freelance"

## Query/Analytics Prompts
`QUERY_FINANCIAL_DATA` enables broad data questions:
- "Show my financial summary"
- "Give me a report from 2026-01-01 to 2026-01-31"
- "What are my top spending categories this month?"
- "Search transactions for uber"
- "Show monthly trend"

## Prompt Reference (All Operations)

### `ADD_EXPENSE`
- "Add an expense of $45 for groceries from account 2 today"
- "I spent $18 on coffee in category 3"

### `ADD_INCOME`
- "Add income $2500 salary to account 1 on 2026-02-14"
- "Record $300 freelance income in category 4"

### `UPDATE_EXPENSE`
- "Update expense ID 102 amount to 65 and description to Team lunch"
- "Change expense 77 date to 2026-02-10"

### `DELETE_EXPENSE`
- "Delete expense ID 102"
- "Yes, delete expense ID 77"

### `GET_MONTHLY_SPENDING`
- "How much did I spend this month?"

### `GET_MONTHLY_INCOME`
- "What is my income this month?"

### `GET_FINANCIAL_SUMMARY`
- "Show my financial summary"

### `GET_SPENDING_BY_CATEGORY`
- "Show spending by category for this month"

### `CREATE_SAVINGS_GOAL`
- "Create a savings goal Trip Fund target $5000 by 2026-12-31 monthly"

### `GET_SAVINGS_PROGRESS`
- "Show my savings progress"

### `ADD_TO_SAVINGS`
- "Add $250 to savings goal 5"

### `WITHDRAW_FROM_SAVINGS`
- "Withdraw $100 from savings goal 5"

### `UPDATE_BUDGET`
- "Update budget 12 amount to 1800 from 2026-02-01 to 2026-02-28"

### `ADD_SUBSCRIPTION`
- "Add subscription Netflix $15 monthly starting 2026-02-01"

### `GET_ACTIVE_SUBSCRIPTIONS`
- "List my active subscriptions"

### `CANCEL_SUBSCRIPTION`
- "Cancel subscription 9 from 2026-03-01"

### `ADD_CREDIT`
- "Add credit card bill $420 due 2026-03-05 interest 2.5"

### `GET_ACTIVE_CREDITS`
- "Show my active credits"

### `MAKE_CREDIT_PAYMENT`
- "Pay $120 for credit 6 on 2026-02-14"

### `TRANSFER_MONEY`
- "Transfer $300 from account 1 to account 2 today for emergency fund"

### `CREATE_RECURRING_TRANSACTION`
- "Create recurring expense Rent $1200 category 2 monthly from 2026-03-01"
- "Create recurring income Salary $2500 category 1 monthly from 2026-03-01"

### `GET_ACTIVE_RECURRING_TRANSACTIONS`
- "Show active recurring transactions"

### `PAUSE_RECURRING_TRANSACTION`
- "Pause recurring transaction 8"
- "Resume recurring transaction 8"

### `DELETE_RECURRING_TRANSACTION`
- "Delete recurring transaction 8"

### `QUERY_FINANCIAL_DATA`
- "Give me a report from 2026-01-01 to 2026-01-31"
- "Show top spending categories this month"
- "Search transactions for uber"
- "Show monthly trend"
- "Show all transactions from 2026-02-01 to 2026-02-14"

### Multi-add examples
- "Add $20 coffee and $50 groceries and $15 parking from account 1"
- "Record income $2000 salary and $300 freelance and $120 interest"

## Conversation APIs
- `GET /api/chat/conversations`
- `GET /api/chat/conversations/paginated`
- `GET /api/chat/conversations/{conversationId}`
- `PUT /api/chat/conversations/{conversationId}`
- `DELETE /api/chat/conversations/{conversationId}`

## Guardrails
- Prompt length checks
- AI response format/safety validation
- Tool-policy operation allowlist
- Per-user rate limiting
- Anomaly and hallucination checks

## Test Notes
- `ChatControllerIntegrationTest` needs a configured model/API key.
- Run all other tests with:
```bash
./mvnw -Dtest='!ChatControllerIntegrationTest' test
```
