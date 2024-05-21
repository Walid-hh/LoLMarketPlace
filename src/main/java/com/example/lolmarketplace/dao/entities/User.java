package com.example.lolmarketplace.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Table(name = "users")
@Data
@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String username;
private String email;
private String password;
}
