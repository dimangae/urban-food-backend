package com.example.Urbanfood.controller;

import com.example.Urbanfood.entity.Cart;
import com.example.Urbanfood.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // GET Cart by ID
    // Example URL: GET http://localhost:8080/api/carts/5
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        Cart cart = cartService.getCartById(id);
        return (cart != null)
                ? ResponseEntity.ok(cart)
                : ResponseEntity.notFound().build();
    }

    // POST to insert a new Cart using URL parameters.
    // Example URL:
    // http://localhost:8080/api/carts?customerId=1&totalItem=3&totalPrice=150.75
    @PostMapping
    public ResponseEntity<Long> insertCart(
            @RequestParam Long customerId,
            @RequestParam Integer totalItem,
            @RequestParam BigDecimal totalPrice) {

        Long newId = cartService.insertCart(customerId, totalItem, totalPrice);
        return ResponseEntity.ok(newId);
    }

    // PUT to update an existing Cart using URL parameters.
    // Example URL:
    // http://localhost:8080/api/carts/5?customerId=1&totalItem=4&totalPrice=180.85
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCart(
            @PathVariable Long id,
            @RequestParam Long customerId,
            @RequestParam Integer totalItem,
            @RequestParam BigDecimal totalPrice) {

        cartService.updateCart(id, customerId, totalItem, totalPrice);
        return ResponseEntity.ok().build();
    }

    // DELETE a Cart by ID
    // Example URL: DELETE http://localhost:8080/api/carts/5
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.ok().build();
    }
}
