package com.example.Urbanfood.controller;

import com.example.Urbanfood.entity.Orders;
import com.example.Urbanfood.service.OrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }
    // Example: POST /api/orders/place?customerId=1&shippingAddress=123 Main Street
    @PostMapping("/place")
    public ResponseEntity<Long> insertOrder(@RequestParam Long customerId,
                                            @RequestParam String shippingAddress) {
        Long orderId = ordersService.insertOrder(customerId, shippingAddress);
        return ResponseEntity.ok(orderId);
    }
    // Example URL: GET /api/orders/calculate-total?orderId=1
    @GetMapping("/calculate-total")
    public ResponseEntity<BigDecimal> calculateOrderTotal(@RequestParam Long orderId) {
        BigDecimal finalTotal = ordersService.calculateOrderTotal(orderId);
        return ResponseEntity.ok(finalTotal);
    }
    // Example URL: POST /api/orders/deduct-stock?productId=1&quantity=5
    @PostMapping("/deduct-stock")
    public ResponseEntity<Void> deductStockOnOrder(@RequestParam Long productId,
                                                   @RequestParam Integer quantity) {
        ordersService.deductStockOnOrder(productId, quantity);
        return ResponseEntity.ok().build();
    }

    // GET order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        Orders order = ordersService.getOrderById(id);
        return (order != null)
                ? ResponseEntity.ok(order)
                : ResponseEntity.notFound().build();
    }

    // POST to insert a new order using URL parameters
    // Example URL:
    // http://localhost:8080/api/orders?customerId=1&shippingAddress=123%20Main%20St&totalAmount=250.75
    @PostMapping
    public ResponseEntity<Long> insertOrder(
            @RequestParam Long customerId,
            @RequestParam String shippingAddress,
            @RequestParam BigDecimal totalAmount) {

        Long newOrderId = ordersService.insertOrder(customerId, shippingAddress, totalAmount);
        return ResponseEntity.ok(newOrderId);
    }

    // PUT to update an existing order using URL parameters
    // Example URL:
    // http://localhost:8080/api/orders/1?customerId=1&orderDate=2025-04-15&shippingAddress=456%20Elm%20St&totalAmount=300.50
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrder(
            @PathVariable Long id,
            @RequestParam Long customerId,
            @RequestParam String orderDate,    // we'll parse this string into a Date
            @RequestParam String shippingAddress,
            @RequestParam BigDecimal totalAmount) {

        // Assuming the date is passed as YYYY-MM-DD
        Date parsedOrderDate;
        try {
            parsedOrderDate = new SimpleDateFormat("yyyy-MM-dd").parse(orderDate);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }

        ordersService.updateOrder(id, customerId, parsedOrderDate, shippingAddress, totalAmount);
        return ResponseEntity.ok().build();
    }

    // DELETE an order by ID
    // Example URL: http://localhost:8080/api/orders/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        ordersService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }

    // GET all orders
    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = ordersService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}
