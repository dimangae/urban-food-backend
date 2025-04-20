package com.example.Urbanfood.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Order_Item")
public class OrderItem {

    @EmbeddedId
    private OrderItemPK id;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "Unit_Price", nullable = false)
    private BigDecimal unitPrice;

    public OrderItem() {}

    public OrderItem(OrderItemPK id, Integer quantity, BigDecimal unitPrice) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public OrderItemPK getId() {
        return id;
    }
    public void setId(OrderItemPK id) {
        this.id = id;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
