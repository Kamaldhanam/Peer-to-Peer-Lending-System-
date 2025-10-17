DELIMITER //

CREATE TRIGGER loan_due_alert
AFTER INSERT ON loans
FOR EACH ROW
BEGIN
    DECLARE due_date DATE;
    SET due_date = NEW.start_date + INTERVAL NEW.term_months MONTH;

    INSERT INTO repayments (loan_id, amount, repayment_date)
    VALUES (NEW.id, NEW.principal * (1 + NEW.interest_rate / 100), due_date);
END//

DELIMITER ;
