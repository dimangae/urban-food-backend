package com.example.Urbanfood.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Cart_Item")
public class CartItem {

    @EmbeddedId
    private CartItemPK id;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Temporal(TemporalType.DATE)
    @Column(name = "Date_Added")
    private Date dateAdded;

    public CartItem() { }

    public CartItem(CartItemPK id, Integer quantity, Date dateAdded) {
        this.id = id;
        this.quantity = quantity;
        this.dateAdded = dateAdded;
    }

    public CartItemPK getId() {
        return id;
    }
    public void setId(CartItemPK id) {
        this.id = id;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Date getDateAdded() {
        return dateAdded;
    }
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
