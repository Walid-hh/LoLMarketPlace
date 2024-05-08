package com.example.lolmarketplace.dao.repositories;

import com.example.lolmarketplace.dao.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
