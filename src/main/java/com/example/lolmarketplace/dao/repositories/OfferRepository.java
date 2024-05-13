package com.example.lolmarketplace.dao.repositories;

import com.example.lolmarketplace.dao.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer> {
    Offer findByOfferName(String offerName);
    Void deleteOfferByOfferName(String offerName);
}
