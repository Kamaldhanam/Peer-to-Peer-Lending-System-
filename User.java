package p2p;

public class User {
    private int id;
    private String name;
    private String email;
    private int creditScore;

    public User(int id, String name, String email, int creditScore) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.creditScore = creditScore;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getCreditScore() { return creditScore; }

    public void setCreditScore(int score) { this.creditScore = score; }
}
