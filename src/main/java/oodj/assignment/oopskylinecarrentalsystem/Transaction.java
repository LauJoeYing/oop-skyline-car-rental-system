package oodj.assignment.oopskylinecarrentalsystem;

public class Transaction {
    private User currentUser;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void payment() {
        System.out.println("\nChecking Balance Amount ...");
        System.out.println("=== Payment Page ===");
        System.out.println("Balance Amount:" + currentUser.getBalance());

    }
}

