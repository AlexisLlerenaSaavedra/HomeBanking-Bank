package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Loan;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String email;

    private Set<AccountDTO> accounts;
    private List<ClientLoanDTO> loans;
    private Set<CardDTO> cards;




    public ClientDTO(Client client){
        this.id= client.getId();
        this.firstName= client.getFirstName();
        this.lastName= client.getLastName();
        this.email= client.getEmail();
        this.accounts=client.getAccounts().stream().map( account -> new AccountDTO(account)).collect(Collectors.
                toSet());
        this.loans=client.getLoans().stream().map(loan -> new ClientLoanDTO(loan)).collect(Collectors.toList());
        this.cards=client.getCards().stream().map(card -> new CardDTO(card)).collect(Collectors.toSet());
    }


    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public List<ClientLoanDTO> getLoans() {
        return loans;
    }

    public Set<CardDTO> getCards() {return cards;}
}
