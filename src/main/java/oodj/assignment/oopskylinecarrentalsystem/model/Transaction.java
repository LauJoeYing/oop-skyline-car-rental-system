package oodj.assignment.oopskylinecarrentalsystem.model;

import oodj.assignment.oopskylinecarrentalsystem.interfaces.FileWrite;
import oodj.assignment.oopskylinecarrentalsystem.interfaces.Searchable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//OOP Implemented: Encapsulation Abstraction and Polymorphism
public class Transaction implements FileWrite, Searchable {
    private final UUID id;
    private final LocalDateTime date;
    private final String transactionType;
    private final String referenceId;
    private final float amount;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Constructs a new Transaction with the specified reference identifier, description, and amount.
    public Transaction(String referenceId, String description, float amount) {
        this.id = UUID.randomUUID();
        this.date = LocalDateTime.now();
        this.transactionType = description;
        this.referenceId = referenceId;
        this.amount = amount;
    }

    public Transaction(String[] registeredTransaction) {
        this.id = UUID.fromString(registeredTransaction[0]);
        this.date = LocalDateTime.parse(registeredTransaction[1], formatter);
        this.transactionType = registeredTransaction[2];
        this.referenceId = registeredTransaction[3];
        this.amount = Float.parseFloat(registeredTransaction[4]);
    }

    // Returns the unique identifier for this transaction.
    public UUID getId() {
        return id;
    }

    // Returns the date and time when this transaction occurred.
    public LocalDateTime getDate() {
        return date;
    }

    // Returns the type of this transaction.
    public String getTransactionType() {
        return transactionType;
    }

    // Returns the reference identifier for this transaction.
    public String getReferenceId() {
        return referenceId;
    }

    // Returns the amount of this transaction.
    public float getAmount() {
        return amount;
    }


    // OOP Concept: Run-Time Polymorphism
    @Override
    public String fileFormat() {
        return String.join(" || ", id.toString(), date.format(formatter), transactionType, referenceId, String.valueOf(amount));
    }


    // OOP Concept: Run-Time Polymorphism
    @Override
    public List<String> getSearchableProperties() {
        List<String> searchableProperties = new ArrayList<>();
        searchableProperties.add(String.valueOf(id));
        searchableProperties.add(referenceId);
        searchableProperties.add(transactionType);
        return searchableProperties;
    }
}
