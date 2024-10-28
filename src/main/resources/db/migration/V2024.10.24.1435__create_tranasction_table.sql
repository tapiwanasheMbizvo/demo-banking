CREATE TYPE transaction_type AS ENUM ('DEBIT', 'CREDIT');

CREATE TABLE bank_transactions (
    id SERIAL PRIMARY KEY,
    amount DECIMAL(19, 4) NOT NULL,
    account_number VARCHAR(50) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    transaction_type transaction_type NOT NULL
);
