package com.example.Urbanfood.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Delivery_ID")
    private Long id;

    @Column(name = "Order_ID", nullable = false)
    private Long orderId;

    @Temporal(TemporalType.DATE)
    @Column(name = "Delivery_Date")
    private Date deliveryDate;

    @Column(name = "Delivery_Status", length = 50)
    private String deliveryStatus;

    // Default constructor required by JPA
    public Delivery() { }

    // Convenience constructor
    public Delivery(Long orderId, Date deliveryDate, String deliveryStatus) {
        this.orderId = orderId;
        this.deliveryDate = deliveryDate;
        this.deliveryStatus = deliveryStatus;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Date getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public String getDeliveryStatus() {
        return deliveryStatus;
    }
    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
