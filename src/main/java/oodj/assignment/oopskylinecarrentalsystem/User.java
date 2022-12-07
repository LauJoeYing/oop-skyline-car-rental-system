package oodj.assignment.oopskylinecarrentalsystem;




public class User {
    private String username;
    private String password;
    private String name;
    private String gender;
    private String phoneNum;
    private String email;
    private String ic;
    private String balance;

    public User( String[] userDetails ) {
        this.username = userDetails[1];
        this.password = userDetails[2];
        this.name = userDetails[3];
        this.balance = userDetails[4];
        this.gender = userDetails[5];
        this.phoneNum = userDetails[6];
        this.email = userDetails[7];
        this.ic = userDetails[8];
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}

