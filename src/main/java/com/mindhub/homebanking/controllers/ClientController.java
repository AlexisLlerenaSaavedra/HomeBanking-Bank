package com.mindhub.homebanking.controllers;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/api")
public class    ClientController {


    @Autowired
    ClientService clientService;

    @Autowired
    AccountService accountService;


    @GetMapping(path = "/api/clients")
    //@RequestMapping("/api/clients")
    public List<ClientDTO> getClients(){
        return clientService.getClientsDTO();
    }


    @GetMapping(path = "/api/clients/{id}")
    //@RequestMapping("/api/clients/{id}")
    public ClientDTO getClient(@PathVariable long id){
        return clientService.getClient(id);
    }


    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @GetMapping(path ="/api/clients/current" )
    //@RequestMapping("/api/clients/current")

    public ClientDTO getAll(Authentication authentication) {

        return new ClientDTO( clientService.findByEmail(authentication.getName()));

    }
    @Autowired

    private PasswordEncoder passwordEncoder;


    @PostMapping(path ="/api/clients" )
    //@RequestMapping(path = "/api/clients", method = RequestMethod.POST)

    public ResponseEntity<Object> register(

            @RequestParam String firstName, @RequestParam String lastName,

            @RequestParam String email, @RequestParam String password) {



        if (firstName.isEmpty() ) {

            return new ResponseEntity<>("Missing data: firstName", HttpStatus.FORBIDDEN);

        }
        else if ( lastName.isEmpty() ) {

            return new ResponseEntity<>("Missing data: lastName", HttpStatus.FORBIDDEN);

        }
        else if ( email.isEmpty() ) {

            return new ResponseEntity<>("Missing data: email", HttpStatus.FORBIDDEN);

        }
        else if (password.isEmpty()) {

            return new ResponseEntity<>("Missing data password", HttpStatus.FORBIDDEN);

        }



        if (clientService.findByEmail(email) !=  null) {

            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);

        }



        Client client=new Client(firstName, lastName, email, passwordEncoder.encode(password));
        Account account=new Account("VIN-"+getRandomNumber(10000000,109999999), LocalDateTime.now(),
                0.0);
        clientService.saveClient(client);
        client.addAccount(account);
        accountService.saveAccount(account);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }


}
