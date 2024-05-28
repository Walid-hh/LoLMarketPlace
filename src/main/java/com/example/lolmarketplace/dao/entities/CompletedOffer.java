package com.example.lolmarketplace.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CompletedOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String offerName;
    private String details;
    private double price;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "encodedImage", length = 65535,columnDefinition = "mediumtext")
    private String encodedImage;
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDateTime;

}
