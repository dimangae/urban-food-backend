package com.example.Urbanfood.repository;

import com.example.Urbanfood.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Standard CRUD methods are available from JpaRepository.
}
