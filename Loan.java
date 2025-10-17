package p2p;

import java.time.LocalDate;

public class Loan {
    private int id;
    private int userId;
    private double principal;
    private double interestRate;
    private int termMonths;
    private LocalDate startDate;
    private LocalDate dueDate;
    private String status;

    public Loan(int userId, double principal, double interestRate, int termMonths) {
        this.userId = userId;
        this.principal = principal;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.startDate = LocalDate.now();
        this.dueDate = startDate.plusMonths(termMonths);
        this.status = "Pending";
    }

    public double getTotalAmount() {
        return principal * (1 + interestRate / 100);
    }

    public int getUserId() { return userId; }
    public LocalDate getDueDate() { return dueDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
