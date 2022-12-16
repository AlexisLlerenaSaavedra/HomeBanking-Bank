package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;

import java.time.LocalDateTime;

public class TransactionDTO {

    private long id;
    private LocalDateTime creationDate;
    private String description;
    private double amount;
    private TransactionType type;

    public TransactionDTO(Transaction transaction){
        this.id= transaction.getId();
        this.creationDate=transaction.getCreationDate();
        this.description= transaction.getDescription();
        this.amount= transaction.getAmount();
        this.type=transaction.getType();

    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }
}
