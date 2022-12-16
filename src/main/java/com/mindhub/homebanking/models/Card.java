package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private long id;
    private String cardHolder;
    private CardType type;
    private CardColor color;
    private String number;
    private int cvv;
    private LocalDate thruDate,fromDate;

    private boolean cardActive=true;



    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    public Card() {

    }

    public Card( String cardHolder, CardType type, CardColor color, String number, int cvv,LocalDate thruDate,
                 LocalDate fromDate) {

        this.cardHolder = cardHolder;
        this.type = type;
        this.color = color;
        this.number = number;
        this.cvv = cvv;
        this.thruDate=thruDate;
        this.fromDate=fromDate;

    }

    public Card(long id, String cardHolder, CardType type, CardColor color, String number, int cvv,
                LocalDate thruDate, LocalDate fromDate, boolean cardActive, Client client) {
        this.id = id;
        this.cardHolder = cardHolder;
        this.type = type;
        this.color = color;
        this.number = number;
        this.cvv = cvv;
        this.thruDate = thruDate;
        this.fromDate = fromDate;
        this.cardActive = cardActive;
        this.client = client;
    }

    public long getId() {
        return id;
    }


    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDate thruDate) {
        this.thruDate = thruDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isCardActive() {
        return cardActive;
    }

    public void setCardActive(boolean cardActive) {
        this.cardActive = cardActive;
    }
}
