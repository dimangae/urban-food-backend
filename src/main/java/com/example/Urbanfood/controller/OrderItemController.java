package com.example.Urbanfood.controller;

import com.example.Urbanfood.entity.OrderItem;
import com.example.Urbanfood.service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    // GET an order item by composite key (using request parameters)
    // Example URL: GET http://localhost:8080/api/order-items?orderId=1&productId=2
    @GetMapping
    public ResponseEntity<OrderItem> getOrderItem(@RequestParam Long orderId, @RequestParam Long productId) {
        OrderItem orderItem = orderItemService.getOrderItem(orderId, productId);
        return orderItem != null ? ResponseEntity.ok(orderItem) : ResponseEntity.notFound().build();
    }

    // POST to insert an order item via URL parameters
    // Example URL:
    // http://localhost:8080/api/order-items?orderId=1&productId=2&quantity=5&unitPrice=12.34
    @PostMapping
    public ResponseEntity<Void> insertOrderItem(@RequestParam Long orderId,
                                                @RequestParam Long productId,
                                                @RequestParam Integer quantity,
                                                @RequestParam BigDecimal unitPrice) {
        orderItemService.insertOrderItem(orderId, productId, quantity, unitPrice);
        return ResponseEntity.ok().build();
    }

    // PUT to update an order item via URL parameters
    // Example URL:
    // http://localhost:8080/api/order-items?orderId=1&productId=2&quantity=10&unitPrice=11.99
    @PutMapping
    public ResponseEntity<Void> updateOrderItem(@RequestParam Long orderId,
                                                @RequestParam Long productId,
                                                @RequestParam Integer quantity,
                                                @RequestParam BigDecimal unitPrice) {
        orderItemService.updateOrderItem(orderId, productId, quantity, unitPrice);
        return ResponseEntity.ok().build();
    }

    // DELETE an order item, identified by orderId and productId
    // Example URL: DELETE http://localhost:8080/api/order-items?orderId=1&productId=2
    @DeleteMapping
    public ResponseEntity<Void> deleteOrderItem(@RequestParam Long orderId,
                                                @RequestParam Long productId) {
        orderItemService.deleteOrderItem(orderId, productId);
        return ResponseEntity.ok().build();
    }
}
