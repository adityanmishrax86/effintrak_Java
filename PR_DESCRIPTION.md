PR Title: Add SERVICE_FLOW.md — Plain-English service flow / PRD for backend

Summary
- Adds a single-file, plain-English service flow / PRD called `SERVICE_FLOW.md` that documents the backend services, their responsibilities, and the primary runtime flows (auth, transactions, transfers, reporting, notifications, exports, dashboard).

What changed
- Added: `SERVICE_FLOW.md` (project root)
  - Purpose: provide a product-level description of what the backend services do and how they integrate, without requiring readers to dive into code.

Why this change
- The repository contains many services and cross-cutting components (auth, mappers, repositories, DB triggers). Product and non-developer stakeholders need a concise, accurate explanation of backend behavior to make decisions and write requirements.
- `SERVICE_FLOW.md` acts like a PRD / onboarding doc for the backend: it explains main flows, service responsibilities, integration points, and risks/recommendations.

Scope & impact
- Pure documentation: no code changes were made.
- Low risk — this file aids understanding for reviewers, product managers, and new developers.
- Places to check if business logic changes are planned: transfer transactional semantics, whether backend (vs frontend) updates bank balances, and consistency between reporting queries and service rules.

Testing & validation
- N/A (documentation only). Suggested follow-ups for engineering:
  - Run integration tests that exercise transfers and income/expense flows to confirm transactional behavior.
  - Validate that DB triggers (in `src/main/resources/sql/trigger`) do not conflict with service-level balance updates.

Next steps / recommendations
- Add a visual diagram (Mermaid or PNG) generated from `SERVICE_FLOW.md` for inclusion in README or the project's docs site.
- Consider adding a small test plan documenting how to validate money-movement flows (transfer, income, expense) and dashboard/report consistency.
- If desired, I can create an adjacency JSON/CSV for easier automation or generate a sequence diagram for critical flows (e.g., Transfer, Create Income).

Notes for reviewers
- This PR adds a single documentation file. Please review the plain-English descriptions for accuracy and request clarifications if any flow sounds inconsistent with intended behavior.

Suggested reviewers
- Backend maintainers and product owners who own transactions, auth, and reporting.
