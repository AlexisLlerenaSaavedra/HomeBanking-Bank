package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@RestController
//@RequestMapping("/api")
public class AccountController {

    @Autowired
    ClientService clientService;

    @Autowired
    AccountService accountService;

    @GetMapping(path = "/api/accounts")
    //@RequestMapping("/api/accounts")
    public List<AccountDTO> getAccounts(){
        return accountService.getAccounts();
    }

    @GetMapping(path ="/api/accounts/{id}" )
    //@RequestMapping("/api/accounts/{id}")
    public AccountDTO getAccount(@PathVariable long id){
        return  accountService.getAccount(id);
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @PostMapping(path = "/api/clients/current/accounts")
    //@RequestMapping(path = "/api/clients/current/accounts", method = RequestMethod.POST)

    public ResponseEntity<Object> registerAccount(Authentication authentication) {
        Client client = clientService.findByEmail(authentication.getName());
        if (client.getAccounts().size() == 3) {
            return new ResponseEntity<>("No se pueden crear mas cuentas", HttpStatus.FORBIDDEN);
        }
        else {
            Account account=new Account("VIN-"+getRandomNumber(10000000,109999999), LocalDateTime.now(),
                    0.0);
            client.addAccount(account);
            accountService.saveAccount(account);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }


}
