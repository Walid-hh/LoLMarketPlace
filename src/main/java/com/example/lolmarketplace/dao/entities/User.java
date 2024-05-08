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
public class Client {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private String id;
private String username;
private String email;
private String firstName;
private String lastName;
private double phone;
private String password;
private Date birthdate;
}
