package com.mindhub.homebanking.service;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.repositories.CardRepository;

public interface CardService {
    public void saveCard(Card card);
    public Card findCardByNumber(String cardNumber);
}
