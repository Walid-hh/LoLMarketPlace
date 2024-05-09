package com.example.lolmarketplace.services;

import com.example.lolmarketplace.dao.entities.Account;
import com.example.lolmarketplace.dao.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServices implements AccountManager{
    AccountRepository accountRepository;
    @Override
    public Account getAccount(String accountName) {
        Account account = accountRepository.findByAccountName(accountName);
        return account;
    }

    @Override
    public Account addAccount(Account account) {
        account=accountRepository.save(account);
        return account;
    }

    @Override
    public Optional<Account> updateAccount(Account account, String accountName) {
        Optional<Account> account1 = Optional.of(accountRepository.findByAccountName(accountName));
        if (account1.isPresent()){
            account1 = Optional.of(accountRepository.save(account));
        }
        return account1;

    }

    @Override
    public void deleteAccount(String accountName) {
        accountRepository.deleteAccountByAccountName(accountName);
    }
}
