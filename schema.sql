-- SQL schema for JPA models: User, BankAccount, Category, Credit, Expense, Income, Savings, Subscription (PostgreSQL version)

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255),
    is_active BOOLEAN
);

CREATE TABLE bank_accounts (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    balance DOUBLE PRECISION NOT NULL DEFAULT 0.0,
    user_id INTEGER NOT NULL,
    CONSTRAINT fk_bankaccount_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE credits (
    id SERIAL PRIMARY KEY,
    description VARCHAR(100) NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    due_date DATE NOT NULL,
    creditor_id INTEGER,
    type VARCHAR(30) NOT NULL,
    interest_rate DOUBLE PRECISION,
    payment_method VARCHAR(50),
    paid BOOLEAN DEFAULT FALSE,
    user_id INTEGER NOT NULL,
    CONSTRAINT fk_credit_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_credit_creditor FOREIGN KEY (creditor_id) REFERENCES bank_accounts(id)
);

CREATE TABLE expenses (
    id SERIAL PRIMARY KEY,
    description VARCHAR(100) NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    date DATE NOT NULL,
    category_id INTEGER,
    payment_method VARCHAR(50),
    paid_to VARCHAR(100),
    is_recurring BOOLEAN DEFAULT FALSE,
    user_id INTEGER NOT NULL,
    bank_account_id INTEGER NOT NULL,
    CONSTRAINT fk_expense_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_expense_category FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT fk_expense_bankaccount FOREIGN KEY (bank_account_id) REFERENCES bank_accounts(id)
);

CREATE TABLE incomes (
    id SERIAL PRIMARY KEY,
    description VARCHAR(100) NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    date DATE NOT NULL,
    category_id INTEGER NOT NULL,
    source VARCHAR(50),
    note VARCHAR(250),
    user_id INTEGER NOT NULL,
    bank_account_id INTEGER NOT NULL,
    CONSTRAINT fk_income_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_income_category FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT fk_income_bankaccount FOREIGN KEY (bank_account_id) REFERENCES bank_accounts(id)
);

CREATE TABLE savings (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(250),
    balance DOUBLE PRECISION NOT NULL DEFAULT 0.0,
    target_amount DOUBLE PRECISION,
    target_date DATE,
    deposit_frequency VARCHAR(20),
    user_id INTEGER NOT NULL,
    CONSTRAINT fk_savings_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE subscriptions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(250),
    price DOUBLE PRECISION NOT NULL,
    billing_cycle VARCHAR(20) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    is_active BOOLEAN DEFAULT TRUE,
    user_id INTEGER NOT NULL,
    CONSTRAINT fk_subscription_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE budgets (
    id SERIAL PRIMARY KEY,
    amount DOUBLE PRECISION NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    category_id INTEGER,
    user_id INTEGER NOT NULL,
    alert_threshold DOUBLE PRECISION NOT NULL,
    CONSTRAINT fk_budget_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_budget_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE recurring_transactions (
    id SERIAL PRIMARY KEY,
    description VARCHAR(100) NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    type VARCHAR(20) NOT NULL,
    category_id INTEGER,
    bank_account_id INTEGER,
    frequency VARCHAR(20) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    next_due_date DATE NOT NULL,
    payment_method VARCHAR(50),
    paid_to VARCHAR(100),
    source VARCHAR(50),
    note VARCHAR(250),
    is_active BOOLEAN DEFAULT TRUE,
    user_id INTEGER NOT NULL,
    CONSTRAINT fk_recurring_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_recurring_category FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT fk_recurring_bankaccount FOREIGN KEY (bank_account_id) REFERENCES bank_accounts(id)
);

CREATE TABLE transfers (
    id SERIAL PRIMARY KEY,
    amount DOUBLE PRECISION NOT NULL,
    description VARCHAR(250),
    transfer_date DATE NOT NULL,
    from_account_id INTEGER NOT NULL,
    to_account_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    CONSTRAINT fk_transfer_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_transfer_from_account FOREIGN KEY (from_account_id) REFERENCES bank_accounts(id),
    CONSTRAINT fk_transfer_to_account FOREIGN KEY (to_account_id) REFERENCES bank_accounts(id)
);

CREATE TABLE notifications (
    id SERIAL PRIMARY KEY,
    message VARCHAR(250) NOT NULL,
    type VARCHAR(50) NOT NULL,
    is_read BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL,
    user_id INTEGER NOT NULL,
    CONSTRAINT fk_notification_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE notification_preferences (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL UNIQUE,
    budget_alerts BOOLEAN NOT NULL DEFAULT TRUE,
    bill_reminders BOOLEAN NOT NULL DEFAULT TRUE,
    subscription_renewals BOOLEAN NOT NULL DEFAULT TRUE,
    goal_achievements BOOLEAN NOT NULL DEFAULT TRUE,
    low_balance_alerts BOOLEAN NOT NULL DEFAULT TRUE,
    unusual_spending_alerts BOOLEAN NOT NULL DEFAULT TRUE,
    email_notifications BOOLEAN NOT NULL DEFAULT FALSE,
    push_notifications BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_notification_preferences_user FOREIGN KEY (user_id) REFERENCES users(id)
);