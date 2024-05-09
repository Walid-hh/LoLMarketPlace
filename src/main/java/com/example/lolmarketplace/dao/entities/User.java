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
    private
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 int id;
 String username;
 String email;
 String password;
}
