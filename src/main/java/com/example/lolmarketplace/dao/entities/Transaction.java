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
public class Transaction {
    private
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     int id;
     int buyerId;
     int sellerId;
     int accountId;
     String status;

}
