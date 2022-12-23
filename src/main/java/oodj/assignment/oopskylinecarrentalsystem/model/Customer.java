package oodj.assignment.oopskylinecarrentalsystem.model;

import oodj.assignment.oopskylinecarrentalsystem.interfaces.Searchable;

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
    private String gender;
    private Address address;
    private float accountBalance;

    // OOP Concept Implemented: Inheritance & Encapsulation
    // The superclass (User) constructor using the super keyword to initialize the instance variables inherited from the User class.
    public Customer(String username, String password, String name, String emailAddress, String phoneNumber, String icNumber, String gender, Address address) {
        super(username, password, name, emailAddress);
        this.phoneNumber = phoneNumber;
        this.icNumber = icNumber;
        this.gender = gender;
        this.address = address;
        accountBalance = 0.0F;
    }

    // OOP Concept Implemented: Inheritance & Encapsulation
    public Customer(String[] registeredCustomer) {
        super(Arrays.copyOfRange(registeredCustomer, 1, 5));
        this.phoneNumber = registeredCustomer[5];
        this.icNumber = registeredCustomer[6];
        this.gender = registeredCustomer[7];

        String[] addressData = registeredCustomer[8].split(" \\| ");
        this.address = new Address(addressData);

        this.accountBalance = Float.parseFloat(registeredCustomer[9]);
    }


    // Returns the phone number of this customer.
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Sets the phone number of this customer.
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Returns the identity card number of this customer.
    public String getIcNumber() {
        return icNumber;
    }

    // Sets the IC number of this customer.
    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    // Returns the gender of this customer.
    public String getGender() {
        return gender;
    }

    // Sets the Gender of this customer.
    public void setGender(String gender) {
        this.gender = gender;
    }

    // Returns the address of this customer.
    public Address getAddress() {
        return address;
    }

    //  Sets the address of this customer.
    public void setAddress(Address address) {
        this.address = address;
    }

    // Returns the Account Balance of this customer.
    public float getAccountBalance() {
        return accountBalance;
    }

    // Sets the Account Balance of this customer.
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

    // OOP Concept Implemented: Run-Time Polymorphism
    @Override
    public List<String> getSearchableProperties() {
        List<String> searchableProperties = new ArrayList<>();
        searchableProperties.add(getUsername());
        searchableProperties.add(getName());
        searchableProperties.add(getEmailAddress());
        searchableProperties.add(phoneNumber);
        searchableProperties.add(icNumber);

        return searchableProperties;
    }

    // OOP Concept Implemented: Run-Time Polymorphism
    @Override
    public String fileFormat() {                //Overriden method for joining customer attributes in file
        return String.join(" || ", "c", getUsername(), getPassword(), getName(), getEmailAddress(), phoneNumber, icNumber, gender, address.fileFormat(), String.valueOf(getAccountBalance()));
    }
}
