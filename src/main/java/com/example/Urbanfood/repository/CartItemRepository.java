package com.example.Urbanfood.repository;

import com.example.Urbanfood.entity.CartItem;
import com.example.Urbanfood.entity.CartItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemPK> {
    // Standard CRUD methods provided by JpaRepository.
}
