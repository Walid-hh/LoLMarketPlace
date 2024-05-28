package com.example.lolmarketplace.controllers;

import com.example.lolmarketplace.dao.entities.Offer;
import com.example.lolmarketplace.dao.entities.CompletedOffer;
import com.example.lolmarketplace.dao.entities.User;
import com.example.lolmarketplace.dao.repositories.OfferRepository;
import com.example.lolmarketplace.dao.repositories.CompletedOfferRepository;
import com.example.lolmarketplace.dao.repositories.UserRepository;
import com.example.lolmarketplace.services.OfferServices;
import com.example.lolmarketplace.services.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;

    private final CompletedOfferRepository completedOfferRepository;
    private final OfferServices offerServices;
    private final PaymentService paymentService;

    public IndexController(UserRepository userRepository, OfferRepository offerRepository, CompletedOfferRepository completedOfferRepository, OfferServices offerServices, PaymentService paymentService) {
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
        this.completedOfferRepository = completedOfferRepository;
        this.offerServices = offerServices;
        this.paymentService = paymentService;
    }

    @GetMapping("/")
    public String index(Authentication authentication, HttpSession session,Model model) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).get();
        if (user != null) {
            String name = user.getUsername();
            session.setAttribute("username", name);
        }
        session.setAttribute("userid", user.getId());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("balance", user.getBalance());
        //GetOffers
        List<Offer> offers = offerServices.getAllOffers();
        List<Offer> myOffers = offerServices.getOffersByUser(user);
        model.addAttribute("offers", offers);
        model.addAttribute("myOffers", myOffers);
        model.addAttribute("currentUserId", user.getId());
        List<CompletedOffer> completedOffers = completedOfferRepository.findByUser(user); // Adjust this according to your implementation
        model.addAttribute("completedOffers", completedOffers);
        return "index";
    }
    @PostMapping("/update-profile")
    public String updateProfile(Authentication authentication,
                                @RequestParam("newusername") String newUsername,
                                @RequestParam("newemail") String newEmail,
                                HttpSession session) {
        String currentUsername = authentication.getName();
        User user = userRepository.findByUsername(currentUsername).orElse(null);
        if (user != null) {
            user.setUsername(newUsername);
            user.setEmail(newEmail);
            userRepository.save(user);
            session.setAttribute("username", newUsername);
            session.setAttribute("email", newEmail);
        }
        return "redirect:/login";
    }
    @PostMapping("/add-offer")
    public String addOffer(Authentication authentication,
                           @RequestParam("offername") String offerName,
                           @RequestParam("details") String details,
                           @RequestParam("price") double price,
                           @RequestParam("image") MultipartFile image) throws IOException {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).get();
        byte[] imageBytes = image.getBytes();
        offerServices.saveOfferWithImage(offerName, details, price, imageBytes, user);
        return "redirect:/";
    }
    @PostMapping("/delete-offer/{offerId}")
    public String deleteOffer(@PathVariable int offerId) {
        offerServices.deleteOfferById(offerId);
        return "redirect:/"; // Redirect to the home page or wherever appropriate
    }


    @PostMapping("/buy-offer/{offerId}")
    public String buyOffer(@PathVariable int offerId, Authentication authentication, RedirectAttributes redirectAttributes) {
        String username = authentication.getName();
        String message = paymentService.processPayment(offerId, username);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/";
    }
    @PostMapping("/update-offer/{offerId}")
    public String updateOffer(
            @PathVariable int offerId,
            @RequestParam String offerName1,
            @RequestParam String offerDetails,
            @RequestParam double offerPrice,
            @RequestParam("offerImage") MultipartFile offerImage, // Add this parameter for the image
            RedirectAttributes redirectAttributes) {

        try {
            Optional<Offer> optionalOffer = offerServices.getOfferById(offerId);
            if (optionalOffer.isPresent()) {
                Offer offer = optionalOffer.get();
                offer.setOfferName(offerName1);
                offer.setDetails(offerDetails);
                offer.setPrice(offerPrice);

                if (!offerImage.isEmpty()) {
                    byte[] imageData = offerImage.getBytes();
                    String encodedImage = Base64.getEncoder().encodeToString(imageData);
                    offer.setEncodedImage(encodedImage);
                }

                offerRepository.save(offer);
                redirectAttributes.addFlashAttribute("message", "Offer updated successfully!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Offer update failed!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating offer: " + e.getMessage());
        }
        return "redirect:/";
    }

}
