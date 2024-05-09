package com.example.lolmarketplace.services;

import com.example.lolmarketplace.dao.entities.Account;

import java.util.Optional;

public interface AccountManager {
    public Account getAccount(String accountName);
    public Account addAccount(Account account);
    Optional<Account> updateAccount(Account account, String accountName);
    public void deleteAccount(String accountName);


}
