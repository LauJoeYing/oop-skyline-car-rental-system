package oodj.assignment.oopskylinecarrentalsystem.model;

public abstract class User implements FileWrite {
    private String username;
    private String password;
    private String name;
    private String emailAddress;

    public User(String username, String password, String name, String emailAddress) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public User(String[] registeredUser) {
        this.username = registeredUser[0];
        this.password = registeredUser[1];
        this.name = registeredUser[2];
        this.emailAddress = registeredUser[3];
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
