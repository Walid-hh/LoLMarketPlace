package com.example.lolmarketplace.dao.repositories;

import com.example.lolmarketplace.dao.entities.Offer;
import com.example.lolmarketplace.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer> {
    Offer findByOfferName(String offerName);
    Void deleteOfferByOfferName(String offerName);

    List<Offer> findByUser(User user);
}
