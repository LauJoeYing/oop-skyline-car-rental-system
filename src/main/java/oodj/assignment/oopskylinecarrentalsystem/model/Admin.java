package oodj.assignment.oopskylinecarrentalsystem.model;

import java.util.Arrays;

// OOP Concept: Inheritance and Run-Time Polymorphism
// Admin class is extending the User class
// It has access to all fields and methods defined in the User class.
public class Admin extends User {

    // OOP Concept: Inheritance
    public Admin(String username, String password, String name, String email) {
        super(username, password, name, email);
    }

    // To Construct new Admin object with the provided registeredAdmin array.
    // The registeredAdmin array should contain the username, password, name, and email of the admin,
    // The first element of the array (index 0) is ignored.
    public Admin(String[] registeredAdmin) {
        super(Arrays.copyOfRange(registeredAdmin, 1, 5));
    }

    // OOP Concept: Run-Time Polymorphism
    // Provides a specific implementation for a method that is already defined in User superclass
    @Override
    public String fileFormat() {
        return String.join(" || ", "a", getUsername(), getPassword(), getName(), getEmailAddress());
    }
}
