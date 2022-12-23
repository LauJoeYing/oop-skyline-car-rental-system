package oodj.assignment.oopskylinecarrentalsystem.model;

import oodj.assignment.oopskylinecarrentalsystem.interfaces.FileWrite;
import oodj.assignment.oopskylinecarrentalsystem.interfaces.Searchable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Transaction implements FileWrite, Searchable {
    private final UUID id;
    private final LocalDateTime date;
    private final String transactionType;
    private final String referenceId;
    private final float amount;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");         //Format specification for date time string

    public Transaction(String referenceId, String description, float amount) {              //Constructor
        this.id = UUID.randomUUID();
        this.date = LocalDateTime.now();
        this.transactionType = description;
        this.referenceId = referenceId;
        this.amount = amount;
    }

    public Transaction(String[] registeredTransaction) {                    //Overloaded Constructor
        this.id = UUID.fromString(registeredTransaction[0]);
        this.date = LocalDateTime.parse(registeredTransaction[1], formatter);
        this.transactionType = registeredTransaction[2];
        this.referenceId = registeredTransaction[3];
        this.amount = Float.parseFloat(registeredTransaction[4]);
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public float getAmount() {
        return amount;
    }



    @Override
    public String fileFormat() {                    //Overriden Method to join Transaction object attributes
        return String.join(" || ", id.toString(), date.format(formatter), transactionType, referenceId, String.valueOf(amount));
    }

    @Override
    public List<String> getSearchableProperties() {             //Overriden Method to get Searched string property
        List<String> searchableProperties = new ArrayList<>();
        searchableProperties.add(String.valueOf(id));
        searchableProperties.add(referenceId);
        searchableProperties.add(transactionType);
        return searchableProperties;
    }
}
