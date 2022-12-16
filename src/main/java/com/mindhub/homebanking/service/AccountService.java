package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.models.Account;

import java.util.List;

public interface AccountService {

    public List<AccountDTO> getAccounts();
    public AccountDTO getAccount(long id);
    public Account findByNumber(String number);
    public void saveAccount(Account account);

}
