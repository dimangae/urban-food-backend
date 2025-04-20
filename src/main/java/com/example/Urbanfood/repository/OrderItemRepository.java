package com.example.Urbanfood.repository;

import com.example.Urbanfood.entity.OrderItem;
import com.example.Urbanfood.entity.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
    // Additional custom queries if needed.
}
