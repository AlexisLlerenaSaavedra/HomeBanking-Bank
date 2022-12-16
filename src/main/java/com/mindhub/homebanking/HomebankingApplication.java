package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.hibernate.query.criteria.internal.predicate.NegatedPredicateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);


	}
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository,
									  TransactionRepository transactionRepository, LoanRepository loanRepository,
									  ClientLoanRepository clientLoanRepository,CardRepository cardRepository)
	{
		return args -> {

			Client ADMIN = new Client ("ADMIN","ADMIN","ADMIN",
					passwordEncoder.encode("123"));
			Client client1 = new Client("Melba","Morel","melba@mindhub.com",
					passwordEncoder.encode("pocha"));
			Client client2 =new Client("Alexis","Llerena","alexisllerenas799@hotmail.com",
					passwordEncoder.encode("mipocha"));

			clientRepository.save(client1);
			clientRepository.save(client2);
			clientRepository.save(ADMIN);
			Account account1=new Account("VIN001", LocalDateTime.now(),5000.0);
			Account account2=new Account("VIN002",LocalDateTime.now().plusDays(1),7500.0);


			Account account3=new Account("VIN003", LocalDateTime.now(),4000.0);
			Account account4=new Account("VIN004",LocalDateTime.now().plusDays(1),1500.0);

			client1.addAccount(account1);
			client1.addAccount(account2);

			client2.addAccount(account3);
			client2.addAccount(account4);

			accountRepository.save(account1);
			accountRepository.save(account2);
			accountRepository.save(account3);
			accountRepository.save(account4);







			Transaction transaction1= new Transaction(LocalDateTime.now(),"milangas",
					-454.33, TransactionType.debito);
			Transaction transaction2= new Transaction(LocalDateTime.now(),"queso",
					+7854.33, TransactionType.credito);
			Transaction transaction3= new Transaction(LocalDateTime.now(),"coca",
					+7554.33, TransactionType.credito);
			Transaction transaction4= new Transaction(LocalDateTime.now(),"asado",
					+6654.33, TransactionType.credito);
			Transaction transaction5= new Transaction(LocalDateTime.now(),"con paco 2",
					-1654.33, TransactionType.debito);
			Transaction transaction6= new Transaction(LocalDateTime.now(),"locro",
					-3654.33, TransactionType.debito);


			account1.addTransaction(transaction1);
			account1.addTransaction(transaction2);
			account2.addTransaction(transaction3);
			account2.addTransaction(transaction4);
			account3.addTransaction(transaction5);
			account4.addTransaction(transaction6);

			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			transactionRepository.save(transaction3);
			transactionRepository.save(transaction4);
			transactionRepository.save(transaction5);
			transactionRepository.save(transaction6);


			Loan loan1 =new Loan("Hipotecario", 500000.0 , List.of(12, 24,36,48,60));
			Loan loan2 =new Loan("Personal", 100000.0 , List.of(6, 12,24));
			Loan loan3 =new Loan("Automotriz", 300000.0 , List.of(6, 12,24,36));



			loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);

			ClientLoan clientLoan1=new ClientLoan(400000.0,60 ,client1,loan1);
			ClientLoan clientLoan2=new ClientLoan(50000.0,12,client1,loan2);
			ClientLoan clientLoan3=new ClientLoan(100000.0,24,client2,loan2);
			ClientLoan clientLoan4=new ClientLoan(200000.0,36,client2,loan3);




			clientLoanRepository.save(clientLoan1);
			clientLoanRepository.save(clientLoan2);
			clientLoanRepository.save(clientLoan3);
			clientLoanRepository.save(clientLoan4);


			Card card1=new Card("Melba Morel",CardType.DEBITO,CardColor.GOLD,"1257-1235-4564-1236",305,
					LocalDate.now(),LocalDate.now());
			Card card2=new Card("Melba Morel",CardType.CREDITO,CardColor.TITANIUM,"1257-1275-4564-9876",
					236,LocalDate.now(),LocalDate.now());
			Card card3=new Card("Alexis Llerena",CardType.CREDITO,CardColor.SILVER,"1157-1235-4564-3698",
					105,LocalDate.now(),LocalDate.now().plusYears(5));

			client1.addCard(card1);
			client1.addCard(card2);
			client2.addCard(card3);

			cardRepository.save(card1);
			cardRepository.save(card2);
			cardRepository.save(card3);







			System.out.println(client1);
			System.out.println(client2);
			System.out.println(account1);
			System.out.println(account2);
			System.out.println(account3);
			System.out.println(account4);
			System.out.println(transaction1);
			System.out.println(transaction2);
			System.out.println(transaction3);
			System.out.println(transaction4);
			System.out.println(transaction5);
			System.out.println(transaction6);



		};
	}

}
