package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dto.LoanApplicationDTO;
import com.mindhub.homebanking.dto.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import com.mindhub.homebanking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController

public class LoanController {


    @Autowired
    ClientLoanService clientLoanService;
    //ClientLoanRepository clientLoanRepository;
    @Autowired
    ClientService clientService;
    @Autowired
    TransactionService transactionService;


    @Autowired
    LoanService loanService;


    @Autowired
    AccountService accountService;
    @GetMapping(path = "api/loans" )
    //@RequestMapping(value = "api/loans")
    public List<LoanDTO> getLoans(){
        return loanService.getLoans();
    }

    @Transactional
    @PostMapping(path = "/api/loans")
    //@RequestMapping(value = "/api/loans",method = RequestMethod.POST)
    public ResponseEntity<Object> addloan(@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication){
        Client clientAutenticado= clientService.findByEmail(authentication.getName());
        Account accountDestiny=accountService.findByNumber(loanApplicationDTO.getNumberDestiny());
        Loan loanExist=loanService.getLoanbyId(loanApplicationDTO.getIdLoan());

        if(clientAutenticado!=null){
            if(loanApplicationDTO.getAmount()<=0){
                return new ResponseEntity<>("monto 0 o negativo", HttpStatus.FORBIDDEN);
            }
            if(loanApplicationDTO.getNumberDestiny().isEmpty()){
                return new ResponseEntity<>("la cuenta de destino esta vacio",HttpStatus.FORBIDDEN);
            }
            if (loanApplicationDTO.getPayments()==null){
                return  new ResponseEntity<>("los payments estan vacios",HttpStatus.FORBIDDEN);
            }

            if(loanApplicationDTO.getIdLoan()==null){
                return new ResponseEntity<>("monto esta vacio",HttpStatus.FORBIDDEN);
            }
            if(accountDestiny==null){
                return new ResponseEntity<>("no existe la cuenta a cargar el prestamo",HttpStatus.FORBIDDEN);
            }
            if(loanExist==null){
                return  new ResponseEntity<>("tu credidto no exixte",HttpStatus.FORBIDDEN);
            }
            if(loanApplicationDTO.getAmount()>loanExist.getMaxAmount()){
                return new ResponseEntity<>("monto excede a su prestamo pedido", HttpStatus.FORBIDDEN);
            }
            if(!loanExist.getPayments().contains(loanApplicationDTO.getPayments())){
                return new ResponseEntity<>("las cuotas no se encuntra entre las disponibles",HttpStatus.FORBIDDEN);
            }
            if(!clientAutenticado.getAccounts().contains(accountDestiny)){
                return  new ResponseEntity<>("esta cuenta no pertenece a la cuenta autenticada",HttpStatus.FORBIDDEN);
            }
            if(clientAutenticado.getLoans().stream().filter(clientLoan -> clientLoan.getLoan().getName().equals(loanExist.getName())).toArray().length==1){
                return new ResponseEntity<>("este prestamo ya fue solicitado",HttpStatus.FORBIDDEN);
            }

            if(loanApplicationDTO.getIdLoan()==14) {
                ClientLoan loan = new ClientLoan(loanApplicationDTO.getAmount() * 1.20, loanApplicationDTO.getPayments(), clientAutenticado, loanExist);
                clientLoanService.saveClientLoan(loan);

            }
            if (loanApplicationDTO.getIdLoan()==15){
                ClientLoan loan = new ClientLoan(loanApplicationDTO.getAmount() * 1.10, loanApplicationDTO.getPayments(), clientAutenticado, loanExist);
                clientLoanService.saveClientLoan(loan);
            }
            if (loanApplicationDTO.getIdLoan()==16){
                ClientLoan loan = new ClientLoan(loanApplicationDTO.getAmount() * 1.15, loanApplicationDTO.getPayments(), clientAutenticado, loanExist);
                clientLoanService.saveClientLoan(loan);
            }

            Transaction transaction = new Transaction(LocalDateTime.now(), "Prestamo aprobado" + loanExist.getName(), loanApplicationDTO.getAmount(), TransactionType.credito);

            accountDestiny.addTransaction(transaction);
            accountDestiny.setBalance(accountDestiny.getBalance()+loanApplicationDTO.getAmount());

            accountService.saveAccount(accountDestiny);
            transactionService.saveTransaction(transaction);



            return new ResponseEntity<>(HttpStatus.CREATED);

        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }




}
