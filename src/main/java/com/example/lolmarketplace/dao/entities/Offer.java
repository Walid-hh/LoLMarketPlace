package com.example.lolmarketplace.dao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Offer {
    private
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     int id;
    
     String offerName;
     String details;
     double price;
     int sellerId;
}
