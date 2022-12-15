package oodj.assignment.oopskylinecarrentalsystem.model;


public class Wallet {
    private String username;

    private String balance;


    public Wallet(String[] userWalletDetails) {

        for (String userWalletDetail : userWalletDetails) {
            userWalletDetail = userWalletDetail.trim();
        }

        this.username = userWalletDetails[0];
        this.balance = userWalletDetails[1];

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}