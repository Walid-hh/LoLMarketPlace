package com.example.lolmarketplace.services;

import com.example.lolmarketplace.dao.entities.Offer;

import java.util.List;
import java.util.Optional;

public interface OfferManager {
    Offer getOffer(String offerName);
    Offer addOffer(Offer offer);
    Optional<Offer> updateOffer(Offer offer, String offerName);
    void deleteOffer(String offerName);
    List<Offer> getAllOffers();

}
