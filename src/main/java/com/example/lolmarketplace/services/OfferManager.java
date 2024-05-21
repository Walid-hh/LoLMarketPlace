package com.example.lolmarketplace.services;

import com.example.lolmarketplace.dao.entities.Offer;

import java.util.List;
import java.util.Optional;

public interface OfferManager {
    Offer getOffer(String offerName);

    Optional<Offer> updateOffer(Offer offer, String offerName);

    List<Offer> getAllOffers();

}
