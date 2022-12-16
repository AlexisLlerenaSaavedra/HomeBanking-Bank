package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.service.CardService;
import com.mindhub.homebanking.service.ClientService;
import com.mindhub.homebanking.utils.CardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/api")
public class CardController {

    @Autowired
    ClientService clientService;

    @Autowired
    CardService cardService;

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @PostMapping(path ="/api/clients/current/cards")
    //@RequestMapping(path = "/api/clients/current/cards", method = RequestMethod.POST)


    public ResponseEntity<Object> registerCards(Authentication authentication,
            @RequestParam CardColor color, @RequestParam CardType type ) {
        Client client = clientService.findByEmail(authentication.getName());
        String cardNumber = CardUtils.getCardNumber();
        int cvv = CardUtils.getCVV();


        if(client.getCards().stream().filter(card -> card.getType()== type).collect(Collectors.toSet()).size() == 3){
            return new ResponseEntity<>("Llegaste al maximo de tarjetas creadas", HttpStatus.FORBIDDEN);
        }


        Card card=new Card(client.getFirstName()+" "+client.getLastName(),type,color,cardNumber
                ,cvv,LocalDate.now(),LocalDate.now().plusYears(5));
        client.addCard(card);
        cardService.saveCard(card);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PatchMapping("api/clients/current/cards")
    public ResponseEntity<Object> deleteCard(@RequestParam String number,Authentication authentication){
        Client clientCurrent = clientService.findByEmail(authentication.getName());
        Card cardSelect=cardService.findCardByNumber(number);
        if (number.isEmpty()){
            return new ResponseEntity<>("Error, Card number not found.", HttpStatus.FORBIDDEN);
        }
        cardSelect.setCardActive(false);
        cardService.saveCard(cardSelect);
        return new ResponseEntity<>("Card disabled successfully", HttpStatus.ACCEPTED);

    }

}