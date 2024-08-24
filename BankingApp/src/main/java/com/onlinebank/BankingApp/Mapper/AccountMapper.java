package com.onlinebank.BankingApp.Mapper;

import com.onlinebank.BankingApp.Dto.AccountDto;
import com.onlinebank.BankingApp.Model.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account=new Account(
        accountDto.getId(),
        accountDto.getAccountHolderName(),
        accountDto.getBalance()
        );
        return account;

    }
    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto=new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
                );
        return accountDto;
    }
}
