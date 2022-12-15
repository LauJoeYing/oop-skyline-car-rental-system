package oodj.assignment.oopskylinecarrentalsystem.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Transaction implements FileWrite {
    private final UUID id;
    private final LocalDateTime date;
    private final String transactionType;
    private final String referenceId;
    private final float amount;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
    public String fileFormat() {
        return String.join(" || ", id.toString(), date.format(formatter), transactionType, referenceId, String.valueOf(amount));
    }
}
