package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.models.Loan;

import java.util.List;

public class ClientLoanDTO {
    private long id;

    private long id_loan;

    private String name;
    private double amount;
    private Integer payments;


    public ClientLoanDTO(ClientLoan clientLoan){
        this.id=clientLoan.getId();
        this.id_loan=clientLoan.getLoan().getId();
        this.name=clientLoan.getLoan().getName();
        this.amount= clientLoan.getAmount();
        this.payments= clientLoan.getPayments();


    }

    public long getId() {
        return id;
    }

    public long getId_loan() {
        return id_loan;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public Integer getPayments() {
        return payments;
    }
}
