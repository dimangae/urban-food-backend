package com.example.Urbanfood.repository;

import com.example.Urbanfood.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Define additional query methods if needed
}
