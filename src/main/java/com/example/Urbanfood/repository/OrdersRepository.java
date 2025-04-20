package com.example.Urbanfood.repository;

import com.example.Urbanfood.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    // Additional query methods if needed
}
