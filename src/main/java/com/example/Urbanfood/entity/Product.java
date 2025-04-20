package com.example.Urbanfood.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_ID")
    private Long id;

    // You might use @ManyToOne for a relationship, but here we keep it simple.
    @Column(name = "Supplier_ID", nullable = false)
    private Long supplierId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Category")
    private String category;

    @Column(name = "Price", nullable = false)
    private BigDecimal price;

    @Column(name = "Stock_Quantity")
    private Integer stockQuantity;

    // Default constructor required by JPA
    public Product() { }

    // Convenience constructor
    public Product(Long supplierId, String name, String description, String category, BigDecimal price, Integer stockQuantity) {
        this.supplierId = supplierId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters and setters
    public Long getId() { return id; }
    public Long getSupplierId() { return supplierId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public BigDecimal getPrice() { return price; }
    public Integer getStockQuantity() { return stockQuantity; }

    public void setId(Long id) { this.id = id; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }
}
