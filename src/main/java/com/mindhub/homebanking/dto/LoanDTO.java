package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Loan;

import java.util.List;

public class LoanDTO {

    private long id;
    private String name;
    private Double maxAmount;
    private List<Integer> payments;

    public LoanDTO(Loan loan){
        this.id= loan.getId();
        this.name= loan.getName();
        this.maxAmount= loan.getMaxAmount();
        this.payments=loan.getPayments();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }
}
