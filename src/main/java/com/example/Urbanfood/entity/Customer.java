package com.example.Urbanfood.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Customer_ID")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Contact", nullable = false)
    private Long contact;

    @Column(name = "Address")
    private String address;

    // Default constructor required by JPA
    public Customer() { }

    // Convenience constructor
    public Customer(String name, String email, Long contact, String address) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address = address;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Long getContact() { return contact; }
    public String getAddress() { return address; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setContact(Long contact) { this.contact = contact; }
    public void setAddress(String address) { this.address = address; }
}
