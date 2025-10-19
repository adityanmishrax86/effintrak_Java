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
    category_name VARCHAR(50) NOT NULL,
    payment_method VARCHAR(50),
    paid_to VARCHAR(100),
    is_recurring BOOLEAN DEFAULT FALSE,
    user_id INTEGER NOT NULL,
    CONSTRAINT fk_expense_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_expense_category FOREIGN KEY (category_id) REFERENCES categories(id)
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
