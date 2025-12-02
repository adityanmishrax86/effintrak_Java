CREATE OR REPLACE FUNCTION update_bank_total_after_income()
RETURNS trigger AS
$$
BEGIN
    -- Handle INSERT and UPDATE
    IF (TG_OP = 'INSERT' OR TG_OP = 'UPDATE') THEN
        UPDATE bank_accounts
        SET balance = (
            SELECT COALESCE(SUM(amount), 0)
            FROM incomes
            WHERE bank_account_id = NEW.bank_account_id
        )
        WHERE id = NEW.bank_account_id;
    END IF;

    -- Handle DELETE
    IF (TG_OP = 'DELETE') THEN
        UPDATE bank_accounts
        SET balance = (
            SELECT COALESCE(SUM(amount), 0)
            FROM incomes
            WHERE bank_account_id = OLD.bank_account_id
        )
        WHERE id = OLD.bank_account_id;
    END IF;

    RETURN NULL; -- AFTER triggers must return NULL
END;
$$
LANGUAGE plpgsql;
