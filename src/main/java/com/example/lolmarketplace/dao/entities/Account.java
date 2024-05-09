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
public class Account {
    private
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     int id;
    
     String accountName;
     String details;
     String username;
     String password;
     String email;
     double price;
     int sellerId;
}
