package com.onlinebank.BankingApp.Service;

import com.onlinebank.BankingApp.Dao.Bankdao;
import com.onlinebank.BankingApp.Dto.AccountDto;
import com.onlinebank.BankingApp.Mapper.AccountMapper;
import com.onlinebank.BankingApp.Model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceimpl implements accountService{
    private Bankdao accountDao;

    @Override
    public AccountDto getAccountById(Long id) {
        Account account =accountDao.findById(id).orElseThrow(()->new RuntimeException("Account not Found"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double balance) {
        Account account =accountDao.findById(id).orElseThrow(()->new RuntimeException("Account not Found"));
        Double total=account.getBalance()+balance;
        account.setBalance(total);
        Account saveaccount = accountDao.save(account);
        return AccountMapper.mapToAccountDto(saveaccount);
    }
    @Override
    public AccountDto withdraw(Long id, double balance) {
        Account account =accountDao.findById(id).orElseThrow(()->new RuntimeException("Account not Found"));
        if(account.getBalance()<balance){
            throw new RuntimeException("Insufficient balance");
        }
        Double total=account.getBalance()-balance;
        account.setBalance(total);
        Account saveaccount = accountDao.save(account);
        return AccountMapper.mapToAccountDto(saveaccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
       List<Account> accounts= accountDao.findAll();
       return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());

    }

    @Override
    public void delete(Long id) {
        Account account =accountDao.findById(id).orElseThrow(()->new RuntimeException("Account not Found"));
        accountDao.delete(account);
    }

    public AccountServiceimpl(Bankdao accountDao) {

        this.accountDao = accountDao;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
        Account saveAccount=accountDao.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);

    }
}
