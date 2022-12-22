package oodj.assignment.oopskylinecarrentalsystem.model;

import java.util.Arrays;

public class Admin extends User {
    public Admin(String username, String password, String name, String email) {
        super(username, password, name, email);
    }

    public Admin(String[] registeredAdmin) {
        super(Arrays.copyOfRange(registeredAdmin, 1, 5));
    }

    @Override
    public String fileFormat() {
        return String.join(" || ", "a", getUsername(), getPassword(), getName(), getEmailAddress());
    }
}
