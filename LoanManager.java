package p2p;

import java.sql.*;

public class LoanManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/p2p_lending";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "yourpassword";

    public static boolean checkEligibility(User user, double amount) {
        // Simple eligibility: credit score >= 600
        return user.getCreditScore() >= 600;
    }

    public static void createLoan(User user, Loan loan) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO loans (user_id, principal, interest_rate, term_months, due_date, status) VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setInt(1, user.getId());
            stmt.setDouble(2, loan.getPrincipal());
            stmt.setDouble(3, loan.getInterestRate());
            stmt.setInt(4, loan.getTermMonths());
            stmt.setDate(5, Date.valueOf(loan.getDueDate()));
            stmt.setString(6, loan.getStatus());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                int loanId = keys.getInt(1);
                System.out.println("Loan created with ID: " + loanId);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void recordRepayment(int loanId, double amount) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO repayments (loan_id, amount) VALUES (?, ?)"
            );
            stmt.setInt(1, loanId);
            stmt.setDouble(2, amount);
            stmt.executeUpdate();
            conn.close();
            System.out.println("Repayment recorded for loan " + loanId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
