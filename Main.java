package p2p;

public class Main {
    public static void main(String[] args) {
        User user = new User(1, "Kamal", "kamaldhanam888@gmail.com", 650);

        // Upload KYC document to S3
        S3Manager.uploadDocument("documentkamal.pdf", user.getEmail());

        // Check loan eligibility
        double requestedAmount = 1000;
        if (LoanManager.checkEligibility(user, requestedAmount)) {
            Loan loan = new Loan(user.getId(), requestedAmount, 10, 12); // 10% interest, 12 months
            LoanManager.createLoan(user, loan);

            // Record a repayment as an example
            LoanManager.recordRepayment(1, 200);
        } else {
            System.out.println("User not eligible for loan.");
        }
    }
}
