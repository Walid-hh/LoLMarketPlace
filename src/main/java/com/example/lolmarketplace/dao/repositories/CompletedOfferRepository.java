package com.example.lolmarketplace.dao.repositories;

import com.example.lolmarketplace.dao.entities.CompletedOffer;
import com.example.lolmarketplace.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompletedOfferRepository extends JpaRepository<CompletedOffer,Integer> {
    List<CompletedOffer> findByUser(User user);
}
