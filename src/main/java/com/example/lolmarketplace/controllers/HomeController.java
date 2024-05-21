package com.example.lolmarketplace.controllers;

import org.springframework.ui.Model;
import com.example.lolmarketplace.dao.entities.Offer;
import com.example.lolmarketplace.services.OfferServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final OfferServices offerServices;

    public HomeController(OfferServices offerServices) {
        this.offerServices = offerServices;
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<Offer> offers = offerServices.getAllOffers();
        model.addAttribute("offers", offers);
        return "home";
    }
}
