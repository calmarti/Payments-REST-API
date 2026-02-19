package net.calmarti.banking_app.service;

import net.calmarti.banking_app.dto.AccountDto;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    List<AccountDto> findAllAccounts();

    AccountDto findAccountById(Long id);

    AccountDto createAccount(AccountDto accountDto);

    AccountDto deposit(Long id, BigDecimal amount);

    AccountDto withdraw(Long id, BigDecimal amount);

    void delete(Long id);

}
