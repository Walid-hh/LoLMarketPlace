package com.example.lolmarketplace.services;

import com.example.lolmarketplace.dao.entities.CompletedOffer;
import com.example.lolmarketplace.dao.entities.Offer;
import com.example.lolmarketplace.dao.entities.User;
import com.example.lolmarketplace.dao.repositories.OfferRepository;
import com.example.lolmarketplace.dao.repositories.CompletedOfferRepository;
import com.example.lolmarketplace.dao.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentService {


    private final UserRepository userRepository;


    private final OfferRepository offerRepository;
    private final CompletedOfferRepository completedOfferRepository;

    public PaymentService(UserRepository userRepository, OfferRepository offerRepository, CompletedOfferRepository completedOfferRepository) {
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
        this.completedOfferRepository = completedOfferRepository;
    }

    public String processPayment(int offerId, String username) {
        User buyer = userRepository.findByUsername(username).orElse(null);
        Offer offer = offerRepository.findById(offerId).orElse(null);

        if (buyer != null && offer != null && buyer.getBalance() >= offer.getPrice()) {
            // Subtract the price from the buyer's balance
            buyer.setBalance(buyer.getBalance() - offer.getPrice());
            userRepository.save(buyer);

            // Add the price to the seller's balance
            User seller = offer.getUser();
            seller.setBalance(seller.getBalance() + offer.getPrice());
            userRepository.save(seller);
            // Create a transaction
            CompletedOffer completedOffer = new CompletedOffer();
            completedOffer.setUser(buyer);
            completedOffer.setOfferName(offer.getOfferName());
            completedOffer.setDetails(offer.getDetails());
            completedOffer.setPrice(offer.getPrice());
            completedOffer.setEncodedImage(offer.getEncodedImage());
            completedOffer.setTransactionDateTime(new Date());
            completedOfferRepository.save(completedOffer);

            // Delete the offer since it has been bought
            offerRepository.delete(offer);

            return "Payment successful!";
        } else {
            return "Insufficient funds, payment cancelled.";
        }
    }
}
