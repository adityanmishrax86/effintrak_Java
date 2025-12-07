INSERT INTO categories (name) VALUES
    ('Food'),
    ('Transport'),
    ('Utilities'),
    ('Entertainment'),
    ('Healthcare'),
    ('Education'),
    ('Shopping'),
    ('Travel'),
    ('Savings'),
    ('Other');


CREATE TRIGGER trg_update_bank_total
AFTER INSERT or UPDATE or DELETE ON incomes
FOR EACH ROW
EXECUTE FUNCTION update_bank_total_after_income();
