package com.example.Urbanfood.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Payment_ID")
    private Long id;

    @Column(name = "Order_ID", nullable = false)
    private Long orderId;

    @Temporal(TemporalType.DATE)
    @Column(name = "Payment_Date")
    private Date paymentDate;

    @Column(name = "Payment_Method")
    private String paymentMethod;

    @Column(name = "Amount", nullable = false)
    private BigDecimal amount;

    // Default constructor
    public Payment() { }

    // Convenience constructor (optional)
    public Payment(Long orderId, Date paymentDate, String paymentMethod, BigDecimal amount) {
        this.orderId = orderId;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
