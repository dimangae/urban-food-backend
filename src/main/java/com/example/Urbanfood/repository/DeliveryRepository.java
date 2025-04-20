package com.example.Urbanfood.repository;

import com.example.Urbanfood.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    // Additional custom query methods can be defined here if needed.
}
