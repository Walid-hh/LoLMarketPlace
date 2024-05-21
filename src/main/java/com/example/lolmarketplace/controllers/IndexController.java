package com.example.lolmarketplace.controllers;

import com.example.lolmarketplace.dao.entities.Offer;
import com.example.lolmarketplace.dao.entities.User;
import com.example.lolmarketplace.dao.repositories.UserRepository;
import com.example.lolmarketplace.services.OfferServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class IndexController {
    private final UserRepository userRepository;
    private final OfferServices offerServices;

    public IndexController(UserRepository userRepository, OfferServices offerServices) {
        this.userRepository = userRepository;
        this.offerServices = offerServices;
    }

    @GetMapping("/")
    public String index(Authentication authentication, HttpSession session,Model model) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).get();
        if (user != null) {
            String name = user.getUsername();
            session.setAttribute("username", name);
        }
        session.setAttribute("username", username);
        session.setAttribute("email", user.getEmail());
        //GetOffers
        List<Offer> offers = offerServices.getAllOffers();
        List<Offer> myOffers = offerServices.getOffersByUser(user);
        model.addAttribute("offers", offers);
        model.addAttribute("myOffers", myOffers);
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
                           @RequestParam("image") MultipartFile image,
                           HttpSession session) throws IOException {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).get();
        byte[] imageBytes = image.getBytes();
        offerServices.saveOfferWithImage(offerName, details, price, imageBytes, user);
        return "redirect:/";
    }
    @PostMapping("/delete-offer")
    public String deleteOffer(@RequestParam("offerId") int offerId) {
        offerServices.deleteOfferById(offerId);
        return "redirect:/";
    }

}
