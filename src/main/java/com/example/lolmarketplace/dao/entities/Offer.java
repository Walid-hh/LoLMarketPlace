package com.example.lolmarketplace.dao.entities;

import jakarta.persistence.*;
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
     double price;;
     @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
