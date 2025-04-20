package com.example.Urbanfood.controller;

import com.example.Urbanfood.entity.Delivery;
import com.example.Urbanfood.service.DeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    // GET Delivery by ID
    // URL example: GET http://localhost:8080/api/deliveries/1
    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable Long id) {
        Delivery delivery = deliveryService.getDeliveryById(id);
        return (delivery != null) ? ResponseEntity.ok(delivery) : ResponseEntity.notFound().build();
    }

    // POST to insert a new Delivery using URL parameters.
    // Example URL:
    // http://localhost:8080/api/deliveries?orderId=1&deliveryDate=2025-04-20&deliveryStatus=Shipped
    @PostMapping
    public ResponseEntity<Long> insertDelivery(
            @RequestParam Long orderId,
            @RequestParam String deliveryDate,
            @RequestParam String deliveryStatus) {

        Date parsedDate;
        try {
            parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(deliveryDate);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
        Long newId = deliveryService.insertDelivery(orderId, parsedDate, deliveryStatus);
        return ResponseEntity.ok(newId);
    }

    // PUT to update an existing Delivery using URL parameters.
    // Example URL:
    // http://localhost:8080/api/deliveries/1?orderId=1&deliveryDate=2025-04-22&deliveryStatus=Delivered
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDelivery(
            @PathVariable Long id,
            @RequestParam Long orderId,
            @RequestParam String deliveryDate,
            @RequestParam String deliveryStatus) {

        Date parsedDate;
        try {
            parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(deliveryDate);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
        deliveryService.updateDelivery(id, orderId, parsedDate, deliveryStatus);
        return ResponseEntity.ok().build();
    }

    // DELETE Delivery by ID.
    // Example URL: DELETE http://localhost:8080/api/deliveries/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
        return ResponseEntity.ok().build();
    }
}
