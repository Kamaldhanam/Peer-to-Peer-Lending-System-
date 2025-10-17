CREATE DATABASE IF NOT EXISTS p2p_lending;

USE p2p_lending;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    credit_score INT DEFAULT 500
);

CREATE TABLE IF NOT EXISTS loans (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    principal DOUBLE NOT NULL,
    interest_rate DOUBLE NOT NULL,
    term_months INT NOT NULL,
    start_date DATE DEFAULT CURRENT_DATE,
    due_date DATE,
    status VARCHAR(20) DEFAULT 'Pending',
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS repayments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    loan_id INT NOT NULL,
    amount DOUBLE NOT NULL,
    repayment_date DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY(loan_id) REFERENCES loans(id)
);
