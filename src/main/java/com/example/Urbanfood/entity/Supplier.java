package com.example.Urbanfood.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "Supplier")
@NamedStoredProcedureQuery(
        name = "Supplier.insert_supplier",
        procedureName = "insert_supplier",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "pa_name", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "pa_phone", type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "pa_addr", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "ou_supId", type = Integer.class)
        }
)
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Supplier_ID")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Contact", nullable = false)
    private Long contact;

    @Column(name = "Address")
    private String address;

    // Default constructor required by JPA
    public Supplier() {
    }

    // Convenience constructor
    public Supplier(String name, Long contact, String address) {
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
