package com.onlinebank.BankingApp.Controller;

import com.onlinebank.BankingApp.Dto.AccountDto;
import com.onlinebank.BankingApp.Service.accountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class bankingController {

    private final accountService accountService;

    @Autowired
    public bankingController(accountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String,Double> request){
        AccountDto accountDto=accountService.deposit(id,request.get("amount"));
        return new ResponseEntity<>(accountDto,HttpStatus.OK);
    }
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String,Double> request){
        AccountDto accountDto=accountService.withdraw(id,request.get("amount"));
        return new ResponseEntity<>(accountDto,HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts=accountService.getAllAccounts();
        return new ResponseEntity<>(accounts,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.delete(id);
        return new ResponseEntity<>("Account is deleted",HttpStatus.OK);
    }
}
