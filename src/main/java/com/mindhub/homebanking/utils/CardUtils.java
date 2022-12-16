package com.mindhub.homebanking.utils;

import static com.mindhub.homebanking.controllers.CardController.getRandomNumber;

public final class CardUtils {

    public CardUtils() {
    }

    public static int getCVV() {
        int cvv=getRandomNumber(100,1000);
        return cvv;
    }


    public static String getCardNumber() {
        String cardNumber= getRandomNumber(1000,10000)+"-"+getRandomNumber(1000,10000)+"-"+getRandomNumber(1000,10000)+
                "-"+getRandomNumber(1000,10000);
        return cardNumber;
    }
}
