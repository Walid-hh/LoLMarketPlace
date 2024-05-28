package com.example.lolmarketplace.services;
import com.example.lolmarketplace.dao.entities.User;
import com.example.lolmarketplace.dao.entities.Offer;
import com.example.lolmarketplace.dao.repositories.OfferRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServices implements OfferManager {
    private final OfferRepository offerRepository;

    public OfferServices(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public Offer getOffer(String offerName) {
        return offerRepository.findByOfferName(offerName);
    }

    @Override
    public Optional<Offer> updateOffer(Offer offer, String offerName) {
        Optional<Offer> existingOffer = Optional.ofNullable(offerRepository.findByOfferName(offerName));
        if (existingOffer.isPresent()) {
            Offer updatedOffer = existingOffer.get();
            updatedOffer.setOfferName(offer.getOfferName());
            updatedOffer.setDetails(offer.getDetails());
            updatedOffer.setPrice(offer.getPrice());
            updatedOffer.setEncodedImage(offer.getEncodedImage());
            updatedOffer.setUser(offer.getUser());
            return Optional.of(offerRepository.save(updatedOffer));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Offer> getOfferById(int id) {
        return offerRepository.findById(id);
    }


    public void deleteOfferById(int offerId) {
        offerRepository.deleteById(offerId);
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public List<Offer> getOffersByUser(User user) {
        return offerRepository.findByUser(user);
    }

    public void saveOfferWithImage(String offerName, String details, double price, byte[] imageFile, User user) throws IOException {
        Offer offer = new Offer();
        offer.setOfferName(offerName);
        offer.setDetails(details);
        offer.setPrice(price);
        String encodedImage = Base64.getEncoder().encodeToString(imageFile);
        offer.setEncodedImage(encodedImage);
        offer.setUser(user);

        offerRepository.save(offer);
    }
}
