package com.example.Urbanfood.controller;

import com.example.Urbanfood.entity.Payment;
import com.example.Urbanfood.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // GET Payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        return (payment != null)
                ? ResponseEntity.ok(payment)
                : ResponseEntity.notFound().build();
    }

    // POST to insert a new Payment using URL parameters.
    // Example URL:
    // http://localhost:8080/api/payments?orderId=1&paymentMethod=CreditCard&amount=100.50
    @PostMapping
    public ResponseEntity<Long> insertPayment(
            @RequestParam Long orderId,
            @RequestParam String paymentMethod,
            @RequestParam BigDecimal amount) {

        Long newId = paymentService.insertPayment(orderId, paymentMethod, amount);
        return ResponseEntity.ok(newId);
    }

    // PUT to update an existing Payment using URL parameters.
    // Example URL:
    // http://localhost:8080/api/payments/1?orderId=1&paymentDate=2025-04-15&paymentMethod=DebitCard&amount=95.99
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePayment(
            @PathVariable Long id,
            @RequestParam Long orderId,
            @RequestParam String paymentDate,
            @RequestParam String paymentMethod,
            @RequestParam BigDecimal amount) {

        Date parsedDate;
        try {
            parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(paymentDate);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
        paymentService.updatePayment(id, orderId, parsedDate, paymentMethod, amount);
        return ResponseEntity.ok().build();
    }

    // DELETE Payment by ID.
    // Example URL: http://localhost:8080/api/payments/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok().build();
    }
}
