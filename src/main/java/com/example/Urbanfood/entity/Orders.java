package com.example.Urbanfood.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_ID")
    private Long id;

    @Column(name = "Customer_ID", nullable = false)
    private Long customerId;

    @Temporal(TemporalType.DATE)
    @Column(name = "Order_Date")
    private Date orderDate;

    @Column(name = "Shipping_Address")
    private String shippingAddress;

    @Column(name = "Total_Amount")
    private BigDecimal totalAmount;

    // Default constructor
    public Orders() { }

    // Convenience constructor
    public Orders(Long customerId, Date orderDate, String shippingAddress, BigDecimal totalAmount) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.shippingAddress = shippingAddress;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public String getShippingAddress() {
        return shippingAddress;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
