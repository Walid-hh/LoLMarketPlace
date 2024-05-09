package com.example.lolmarketplace.dao.repositories;

import com.example.lolmarketplace.dao.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findByAccountName(String accountName);
    Account findByEmail(String email);
    Void deleteAccountByAccountName(String accountName);
}
