package com.example.lolmarketplace.services;

import com.example.lolmarketplace.dao.entities.Account;

public interface AccountManager {
    public Account getAccount(String accountName);
    public Account addAccount(Account account);
    public Account updateAccount(Account account);
    public String deleteAccount(String accountName);


}
