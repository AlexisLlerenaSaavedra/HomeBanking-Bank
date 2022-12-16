package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.ClientLoan;

import java.util.List;

public class LoanApplicationDTO {

    private Long idLoan;
    private double amount;
    private Integer payments;
    private String numberDestiny;


    public Long getIdLoan() {
        return idLoan;
    }

    public void setIdLoan(long idLoan) {
        this.idLoan = idLoan;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getPayments() {
        return payments;
    }

    public void setPayments(Integer payments) {
        this.payments = payments;
    }

    public String getNumberDestiny() {
        return numberDestiny;
    }

    public void setNumberDestiny(String numberDestiny) {
        this.numberDestiny = numberDestiny;
    }
}


