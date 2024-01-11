CREATE TABLE accounts (
    account_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    account_number VARCHAR(20) UNIQUE NOT NULL,
    account_type VARCHAR(50),
    balance DECIMAL(15, 2) NOT NULL,
    status VARCHAR(50),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE transactions (
    transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT NOT NULL,
    type VARCHAR(50), -- e.g., deposit, withdrawal, transfer
    amount DECIMAL(15, 2),
    transaction_date DATETIME,
    status VARCHAR(50),
    description TEXT,
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);

CREATE TABLE overdrafts (
    overdraft_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT NOT NULL,
    overdraft_limit DECIMAL(15, 2) NOT NULL,
    overdraft_used DECIMAL(15, 2) NOT NULL DEFAULT 0,
    interest_rate DECIMAL(5, 2) NOT NULL,
    start_date DATETIME,
    end_date DATETIME,
    status VARCHAR(50),
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);
CREATE TABLE loans (
    loan_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    loan_amount DECIMAL(15, 2) NOT NULL,
    amount_paid DECIMAL(15, 2) NOT NULL DEFAULT 0,
    interest_rate DECIMAL(5, 2) NOT NULL,
    loan_start_date DATETIME,
    loan_end_date DATETIME,
    monthly_installment DECIMAL(15, 2) NOT NULL,
    status VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(id) -- Assuming 'users' table is in the User Management Service
);

CREATE TABLE loans (
    loan_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT NOT NULL,
    loan_amount DECIMAL(15, 2) NOT NULL,
    amount_paid DECIMAL(15, 2) NOT NULL DEFAULT 0,
    interest_rate DECIMAL(5, 2) NOT NULL,
    loan_start_date DATETIME,
    loan_end_date DATETIME,
    monthly_installment DECIMAL(15, 2) NOT NULL,
    status VARCHAR(50),
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);
