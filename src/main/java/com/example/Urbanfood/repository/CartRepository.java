package com.example.Urbanfood.repository;

import com.example.Urbanfood.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    // Standard CRUD methods are available via JpaRepository.
}
