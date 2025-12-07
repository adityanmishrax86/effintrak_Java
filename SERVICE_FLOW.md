**Service Flow & PRD (Plain English)**

Project: EffinTrak (backend service flows)

Purpose: This document describes, in plain English, how the backend services work together — a high-level flow chart and per-service responsibilities so a non-developer (or product owner) can understand what the code actually does.

---

**Overview**: The app is a typical REST backend. Clients call controllers (HTTP endpoints). Controllers validate input and call Services. Services enforce business rules, call other services or repositories (database), map between DTOs and domain models, and return results to controllers. Cross-cutting features include authentication (JWT + refresh tokens), global response formatting, and centralized error handling.

**High-level flow chart (plain English)**

Client -> Controller -> Service -> (Other Services and/or Repositories) -> Database

- Controllers: accept requests, call Services, return formatted responses.
- Services: implement business logic; may call other Services and Repositories.
- Repositories: talk to the database (persist / query entities).
- Mappers: convert between DTOs (API objects) and Entities (DB models).

Below is a slightly expanded flow that covers the major flows in the app.

1) Authentication flow (login / refresh / logout)
   - Client sends credentials to `UserController`.
   - `UserController` calls `UserService.authenticateUser()` which checks password with `Encoder`.
   - On success, `JWTUtil` creates a JWT access token and `RefreshTokenService` creates & stores a refresh token.
   - Client uses JWT for subsequent API calls; the `JwtAuthFilter` validates JWTs on each request.
   - On logout, `UserService.logout()` calls `RefreshTokenService.deleteByUser()` to invalidate refresh tokens.

2) Create Income / Expense
   - Client posts a new income/expense DTO to `IncomeController` / `ExpenseController`.
   - Controller calls `IncomeService.saveIncome()` or `ExpenseService.saveExpense()`.
   - Service validates amount, date, required fields.
   - Service calls `CategoryService` to verify category, and `BankAccountService` to verify bank account existence.
   - Service maps DTO -> Entity (via `IncomeMapper`/`ExpenseMapper`), sets relationships (user, category, bank account) and saves via repository.
   - (Note) Balance updates may be applied by services or by DB triggers depending on implementation; services call `BankAccountService.saveBankAccount()` when they adjust balances.

3) Transfer between accounts
   - Client requests transfer via `TransferController`.
   - `TransferService.createTransfer()` (transactional) verifies both accounts belong to user and that the source account has sufficient funds.
   - Service subtracts amount from source account, adds to destination account, persists both accounts, and saves a `Transfer` record. Entire operation is atomic (transactional) — either all succeed or none.

4) Recurring transactions / Subscriptions / Bills
   - `RecurringTransactionService` stores patterns (daily, weekly, monthly, yearly) and next-due-date calculation.
   - `SubscriptionService` stores subscriptions with start/end dates and price; `BillService` aggregates overdue credits and subscriptions to present bills.
   - `NotificationService` checks budgets and transactions and creates alerts when budgets are exceeded or bills are upcoming/overdue.

5) Reporting, Dashboard, Export
   - `TransactionService` composes Income + Expense into a common transaction view for timeline and searches.
   - `ReportService` aggregates totals and trends (monthly, category trends) by reading Income/Expense repositories.
   - `ExportService` uses `TransactionService` and `ReportService` to create CSV/text exports for users.
   - `DashboardService` aggregates balances, recent transactions, budgets, upcoming bills, savings progress by reading multiple repositories and calling `TransactionService`.

6) Notifications
   - `NotificationService` reads budgets/expenses and creates `Notification` entities when thresholds are hit. It also manages user preferences (enable/disable certain alerts) and unread counts.

**Service responsibility list (plain English, one line each)**
- `UserService`: register/login/logout users; check credentials; provide user lookup.
- `RefreshTokenService`: create, validate, delete refresh tokens stored in DB.
- `BankAccountService`: CRUD for bank accounts and mapping to API responses.
- `IncomeService`: validate and save incomes, link to category & bank account, provide paged queries.
- `ExpenseService`: validate and save expenses, link to category & bank account, provide paged queries.
- `CategoryService`: CRUD for categories and provides category lists used across transactions.
- `TransferService`: move money between two accounts (checks ownership, balance), persist transfer — atomic.
- `CreditService`: manage credit or loan-like records (due dates, paid/unpaid, creditor bank account).
- `SavingsService`: manage savings goals/accounts and progress targets.
- `SubscriptionService`: create/update subscriptions with start and end dates and renewal info.
- `RecurringTransactionService`: schedule and manage recurring incomes/expenses and next-due-date logic.
- `TransactionService`: combine incomes and expenses into a single transaction feed for UI and exports.
- `ReportService`: compute totals, category trends and monthly trends for reporting UI.
- `ExportService`: convert transactions or reports into CSV or text for download.
- `DashboardService`: aggregate a user’s balances, recent transactions, budgets, upcoming bills and savings progress for the dashboard view.
- `NotificationService`: create alerts when budgets are exceeded, manage notification preferences.
- `GlobalResponseService`: standardize API success/error responses across controllers.

**Cross-cutting implementation notes (what actually happens under the hood)**
- Authentication: password hashing via `Encoder`, JWT tokens for stateless auth, refresh token persistence for long-lived sessions.
- Date handling: almost all services expect `yyyy-MM-dd` strings; they parse into `LocalDate` or `java.sql.Date`.
- Mapping: mappers convert between DTOs and entities — services use mappers before saving or returning data.
- Read vs Write separation: Reporting/dashboard services often read repositories directly for performance and aggregation instead of calling domain services.
- Transactions: multi-entity updates (like transfers) are done inside database transactions to ensure consistency.
- DB triggers: there are SQL triggers in the project that can also update bank totals — the DB layer may contain logic complementary to service-level updates.

**Common flows (quick reference)**
- "Create income": Controller -> `IncomeService` -> validate -> `CategoryService` & `BankAccountService` lookups -> save Income -> (optionally update bank balance) -> return DTO.
- "Transfer": Controller -> `TransferService` (transactional) -> validate ownership & balance -> update accounts -> save transfer -> return transfer DTO.
- "Dashboard": Controller -> `DashboardService` -> read multiple repositories and `TransactionService` -> compose dashboard DTO -> return.

**Recommendations / Risks for product owners**
- Keep in mind whether balance updates are performed in the back end or assumed by front-end JS — the backend should be the source of truth for money movements.
- Reports & dashboards read data directly from repositories: if business rules change, both services and reporting queries may need updates to stay consistent.
- If you plan to extend notification rules or scheduled jobs, the `NotificationService` and `RecurringTransactionService` are the natural places to add that logic.

---

File location: `SERVICE_FLOW.md` (project root)

If you'd like, I can:
- Generate a visual flow diagram (Mermaid or PNG) from this map.
- Produce a condensed adjacency CSV/JSON listing services -> dependencies.
- Create a small README or PR text to include with a code change.

Tell me which of the above you want next.
