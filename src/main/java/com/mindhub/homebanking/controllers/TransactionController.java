package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.service.ClientService;
import com.mindhub.homebanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
@RestController


public class TransactionController {

    @Autowired
    AccountService accountService;


    @Autowired
    ClientService clientService;

    @Autowired
    TransactionService transactionService;

    @Transactional
    @PostMapping(path = "/api/transaction")
    //@RequestMapping(path = "/api/transaction", method = RequestMethod.POST)
    public ResponseEntity<Object> registerTransactions (@RequestParam Double amount ,@RequestParam String description,
                 @RequestParam String origin ,@RequestParam String destiny , Authentication authentication) {
        Client client = clientService.findByEmail(authentication.getName());
        Account accountOri = accountService.findByNumber(origin);
        Account accountDest = accountService.findByNumber(destiny);
        Set<Account> accountClientExist = client.getAccounts().stream().filter(account -> account.getNumber().equals(origin)).collect(Collectors.toSet());


        if (amount <= 0) {
            return new ResponseEntity<>("Invalid amount", HttpStatus.FORBIDDEN);
        }

        if (description.isEmpty()) {
            return new ResponseEntity<>("Missing description", HttpStatus.FORBIDDEN);
        }

        if (origin.isEmpty() ) {
            return new ResponseEntity<>("missing accountOrigin", HttpStatus.FORBIDDEN);
        }

        if (destiny.isEmpty() ) {
            return new ResponseEntity<>("missing accountDestiny", HttpStatus.FORBIDDEN);
        }


        if (accountClientExist.size()==0 ) {
            return new ResponseEntity<>("missing account", HttpStatus.FORBIDDEN);
        }



        if (origin.equals(destiny)) {
            return new ResponseEntity<>("Account origin and destiny are the same", HttpStatus.FORBIDDEN);
        }



        if (accountOri == null) {
            return new ResponseEntity<>("Origin account doesn't exist", HttpStatus.FORBIDDEN);
        }
        if (accountDest == null) {
            return new ResponseEntity<>("Destiny account doesn't exist", HttpStatus.FORBIDDEN);
        }

        if (accountService.findByNumber(origin).getBalance() < amount) {
            return new ResponseEntity<>("Not enough money", HttpStatus.FORBIDDEN);
        }


        Transaction transactionOrigen = new Transaction(LocalDateTime.now(), description + " " ,amount, TransactionType.debito);
        Transaction transactionDestino = new Transaction(LocalDateTime.now(), description + " " ,amount, TransactionType.credito);



        accountOri.addTransaction(transactionOrigen);
        accountDest.addTransaction(transactionDestino);

        transactionService.saveTransaction(transactionOrigen);
        transactionService.saveTransaction(transactionDestino);

        accountOri.setBalance(accountOri.getBalance() - amount);
        accountDest.setBalance(accountDest.getBalance() + amount);

        accountService.saveAccount(accountOri);
        accountService.saveAccount(accountDest);


        return new ResponseEntity<>(HttpStatus.CREATED);

    }




}
