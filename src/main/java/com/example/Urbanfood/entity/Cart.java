package com.example.Urbanfood.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Cart_ID")
    private Long id;

    @Column(name = "Customer_ID", nullable = false)
    private Long customerId;

    @Column(name = "Total_item")
    private Integer totalItem;  // Using Integer so it can be null if needed

    @Column(name = "Total_Price", nullable = false)
    private BigDecimal totalPrice;

    // Default constructor required by JPA
    public Cart() { }

    // Convenience constructor (optional)
    public Cart(Long customerId, Integer totalItem, BigDecimal totalPrice) {
        this.customerId = customerId;
        this.totalItem = totalItem;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public Integer getTotalItem() {
        return totalItem;
    }
    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
