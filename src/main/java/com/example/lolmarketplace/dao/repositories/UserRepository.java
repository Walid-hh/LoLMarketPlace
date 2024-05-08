package com.example.lolmarketplace.dao.repositories;

import com.example.lolmarketplace.dao.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,String> {
}
