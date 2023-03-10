package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Account;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {

    private long id;
    private String number;
    private LocalDateTime creationDate;
    private double balance;

    private Set<TransactionDTO>transactionDTOS;

    public AccountDTO(Account account){
        this.id=account.getId();
        this.number=account.getNumber();
        this.creationDate=account.getCreationDate();
        this.balance=account.getBalance();
        this.transactionDTOS=account.getTransactions().stream().map(transaction -> new TransactionDTO(transaction)).collect(Collectors.toSet());

    }

    public Set<TransactionDTO> getTransactionDTOS() {
        return transactionDTOS;
    }

    public long getId() {return id;}

    public String getNumber() {return number;}

    public void setNumber(String number) {this.number = number;}

    public LocalDateTime getCreationDate() {return creationDate;}

    public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

    public double getBalance() {return balance;}

    public void setBalance(Double balance) {this.balance = balance;}
}
