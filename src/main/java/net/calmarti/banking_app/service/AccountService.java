package net.calmarti.banking_app.service;

import net.calmarti.banking_app.dto.AccountDto;

import java.util.List;

public interface AccountService {

    List<AccountDto> obtainAllAccounts();

    AccountDto obtainAccountDetails(Long id);

    AccountDto openNewAccount(AccountDto accountDto);

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    void delete(Long id);

}
