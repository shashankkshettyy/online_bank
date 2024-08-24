package com.onlinebank.BankingApp.Service;

import com.onlinebank.BankingApp.Dto.AccountDto;
import com.onlinebank.BankingApp.Model.Account;

import java.util.List;

public interface accountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, double balance);

    AccountDto withdraw(Long id, double balance);

    List<AccountDto> getAllAccounts();

    void delete(Long id);



}
