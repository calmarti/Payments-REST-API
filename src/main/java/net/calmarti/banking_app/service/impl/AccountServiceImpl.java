package net.calmarti.banking_app.service.impl;

import net.calmarti.banking_app.dto.AccountDto;
import net.calmarti.banking_app.entity.Account;
import net.calmarti.banking_app.exception.AccountException;
import net.calmarti.banking_app.mapper.AccountMapper;
import net.calmarti.banking_app.repository.AccountRepository;
import net.calmarti.banking_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired //not necessary since there's just one constructor
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountDto> obtainAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map((account -> AccountMapper.mapToAccountDto(account)))
                .toList();
    }

    @Override
    public AccountDto obtainAccountDetails(Long id) {
        if (id == null) throw new IllegalArgumentException("Account id must not be null");

        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new AccountException(("Account with id " + id + " does not exist")));
                                  //new NoSuchElementException("Account with id " + id + " was not found"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto openNewAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }


    @Override
    @Transactional
    public AccountDto deposit(Long id, double amount) {
        if (id == null) throw new IllegalArgumentException("Account id must not be null");
        if (amount < 0) throw new IllegalArgumentException("Amount must be non-negative");

        //TODO: validate amount must be non-negative double
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new AccountException("Account with id " + id + " does not exist"));
                                  //new NoSuchElementException("Account with id " + id + " does not exist"));
        account.setBalance(account.getBalance() + amount);
        Account savedAccount = accountRepository.save(account); //We call "save" for educational purposes but with @Transactional this is not required
        return AccountMapper.mapToAccountDto(savedAccount);
    }


    @Override
    @Transactional
    public AccountDto withdraw(Long id, double amount) {
        if (id == null) throw new IllegalArgumentException("Account id must not be null");
        if (amount < 0) throw new IllegalArgumentException("Amount must be non-negative");

        Account account = accountRepository.findById(id)
                .orElseThrow(()->  new AccountException("Account with id " + id + " does not exist"));
                                   //new NoSuchElementException("Account with id = " + id + " does not exist"));

        if (account.getBalance() < amount) throw new IllegalArgumentException("Withdrawal amount must not be greater than current balance");

        account.setBalance(account.getBalance() - amount);
        //With @Transactional, we don't really need to call "save"
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null) throw new IllegalArgumentException("Account id must not be null");
        accountRepository.findById(id)
                .orElseThrow(()-> new AccountException("Account with id " + id + " does not exist"));
                                  //new NoSuchElementException("Account with id = " + id + " does not exist"));
        accountRepository.deleteById(id);
    }
}
