package net.calmarti.banking_app.mapper;

import net.calmarti.banking_app.dto.AccountDto;
import net.calmarti.banking_app.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(null,
                accountDto.accountHolderName(),
                accountDto.balance());
        return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance());
        return accountDto;
    }
}
