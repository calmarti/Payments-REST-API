package net.calmarti.banking_app.controller;

import net.calmarti.banking_app.dto.AccountDto;
import net.calmarti.banking_app.dto.UpdateBalanceDto;
import net.calmarti.banking_app.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        return ResponseEntity.ok(accountService.obtainAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto account = accountService.obtainAccountDetails(id);
        return new ResponseEntity<>(account,HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
        AccountDto createdAccount = accountService.openNewAccount(accountDto);
        return new ResponseEntity<>(createdAccount,HttpStatus.CREATED);
    }

    @CrossOrigin
    @PatchMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> depositToAccountById(@PathVariable Long id, @RequestBody UpdateBalanceDto request) {
        AccountDto account = accountService.deposit(id, request.amount());
        return new ResponseEntity<AccountDto>(account, HttpStatus.OK);
        //return ResponseEntity.ok(account); //this would also work
    }

    @CrossOrigin
    @PatchMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawFromAccountById(@PathVariable Long id, @RequestBody UpdateBalanceDto request) {
        AccountDto account = accountService.withdraw(id, request.amount());
        return new ResponseEntity<AccountDto>(account, HttpStatus.OK);
        //return ResponseEntity.ok(account); //this would also work
    }

   @CrossOrigin
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteAccountById(@PathVariable Long id){
        accountService.delete(id);
        return ResponseEntity.noContent().build();
   }
}
