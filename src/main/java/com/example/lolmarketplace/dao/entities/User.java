package com.example.lolmarketplace.dao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    private
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 String id;
 String username;
 String email;
 String password;
}
