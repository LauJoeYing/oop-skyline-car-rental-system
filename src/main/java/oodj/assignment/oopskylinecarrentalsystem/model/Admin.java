package oodj.assignment.oopskylinecarrentalsystem.model;

import java.util.Arrays;

public class Admin extends User {                   //Child Class of user type Admin
    public Admin(String username, String password, String name, String email) {
        super(username, password, name, email);
    }

    public Admin(String[] registeredAdmin) {    //Constructor of admin class
        super(Arrays.copyOfRange(registeredAdmin, 1, 5));
    }

    @Override
    public String fileFormat() {            //Overriden admin details format in file method
        return String.join(" || ", "a", getUsername(), getPassword(), getName(), getEmailAddress());
    }
}
