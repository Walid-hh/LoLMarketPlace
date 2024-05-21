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
public class Payment {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 private int transactionId;
 private double amount;
 private String status;

}
