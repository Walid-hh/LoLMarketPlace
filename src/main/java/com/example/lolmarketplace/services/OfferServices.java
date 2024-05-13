package com.example.lolmarketplace.services;

import com.example.lolmarketplace.dao.entities.Offer;
import com.example.lolmarketplace.dao.repositories.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfferServices implements OfferManager{
    OfferRepository offerRepository;
    @Override
    public Offer getOffer(String offerName) {
        return offerRepository.findByOfferName(offerName);
    }

    @Override
    public Offer addOffer(Offer offer) {
        offer = offerRepository.save(offer);
        return offer;
    }

    @Override
    public Optional<Offer> updateOffer(Offer offer, String offerName) {
        Optional<Offer> offer1 = Optional.of(offerRepository.findByOfferName(offerName));
        if (offer1.isPresent()){
            offer1 = Optional.of(offerRepository.save(offer));
        }
        return offer1;

    }

    @Override
    public void deleteOffer(String offerName) {
        offerRepository.deleteOfferByOfferName(offerName);
    }
}
