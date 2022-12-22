package oodj.assignment.oopskylinecarrentalsystem.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// OOP Concept Implemented:
// Inheritance: The class Customer extends the class User, which means that it inherits all the properties and methods of the User class.
// Polymorphism: The class Customer implements the interface Searchable.
// Encapsulation: The class Customer has several private instance variables which are only accessible through public methods.
// Abstraction: The interface Searchable defines a set of methods that must be implemented by any class that implements it, but does not provide any implementations for those methods.
public class Customer extends User implements Searchable {
    private String phoneNumber;
    private String icNumber;
    private Address address;
    private float accountBalance;
    private boolean isBlackListed;


    // OOP Concept Implemented: Inheritance & Encapsulation
    // The superclass (User) constructor using the super keyword to initialize the instance variables inherited from the User class.
    public Customer(String username, String password, String name, String emailAddress, String phoneNumber, String icNumber, Address address) {
        super(username, password, name, emailAddress);
        this.phoneNumber = phoneNumber;
        this.icNumber = icNumber;
        this.address = address;
        accountBalance = 0.0F;
        isBlackListed = false;
    }

    // OOP Concept Implemented: Inheritance & Encapsulation
    public Customer(String[] registeredCustomer) {
        super(Arrays.copyOfRange(registeredCustomer, 1, 5));
        this.phoneNumber = registeredCustomer[5];
        this.icNumber = registeredCustomer[6];

        String[] addressData = registeredCustomer[7].split(" \\| ");
        this.address = new Address(addressData);

        this.accountBalance = Float.parseFloat(registeredCustomer[8]);
        this.isBlackListed = Boolean.parseBoolean(registeredCustomer[9]);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public float getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(float accountBalance) {
        this.accountBalance = accountBalance;
    }


    // To add the current customer account balance
    public void addAccountBalance(float amount) {
        this.accountBalance += amount;
    }

    // To deduct the current customer account balance
    public void deductAccountBalance(float amount) {
        this.accountBalance -= amount;
    }

    public boolean isBlackListed() {
        return isBlackListed;
    }

    public void setBlackListed(boolean blackListed) {
        isBlackListed = blackListed;
    }

    // OOP Concept Implemented: Compiled-Time Polymorphism
    public List<String> getSearchableProperties() {
        List<String> searchableProperties = new ArrayList<>();
        searchableProperties.add(getUsername());
        searchableProperties.add(getName());
        searchableProperties.add(getEmailAddress());
        searchableProperties.add(phoneNumber);
        searchableProperties.add(icNumber);

        return searchableProperties;
    }

    @Override
    public String fileFormat() {
        return String.join(" || ", "c", getUsername(), getPassword(), getName(), getEmailAddress(), phoneNumber, icNumber, address.fileFormat(), String.valueOf(getAccountBalance()), Boolean.toString(isBlackListed));
    }
}



