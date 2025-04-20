package com.example.Urbanfood.controller;

import com.example.Urbanfood.entity.CartItem;
import com.example.Urbanfood.service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    // GET an individual cart item by composite key (using URL parameters)
    // Example URL: GET http://localhost:8080/api/cart-items?cartId=1&productId=2
    @GetMapping
    public ResponseEntity<CartItem> getCartItem(@RequestParam Long cartId, @RequestParam Long productId) {
        CartItem cartItem = cartItemService.getCartItem(cartId, productId);
        return cartItem != null ? ResponseEntity.ok(cartItem) : ResponseEntity.notFound().build();
    }

    // POST to insert a new cart item using URL parameters.
    // Example URL: POST http://localhost:8080/api/cart-items?cartId=1&productId=2&quantity=3&dateAdded=2025-04-15
    @PostMapping
    public ResponseEntity<Void> insertCartItem(
            @RequestParam Long cartId,
            @RequestParam Long productId,
            @RequestParam Integer quantity,
            @RequestParam String dateAdded) {

        Date parsedDate;
        try {
            parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateAdded);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
        cartItemService.insertCartItem(cartId, productId, quantity, parsedDate);
        return ResponseEntity.ok().build();
    }

    // PUT to update an existing cart item using URL parameters.
    // Example URL: PUT http://localhost:8080/api/cart-items?cartId=1&productId=2&quantity=5&dateAdded=2025-04-16
    @PutMapping
    public ResponseEntity<Void> updateCartItem(
            @RequestParam Long cartId,
            @RequestParam Long productId,
            @RequestParam Integer quantity,
            @RequestParam String dateAdded) {

        Date parsedDate;
        try {
            parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateAdded);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
        cartItemService.updateCartItem(cartId, productId, quantity, parsedDate);
        return ResponseEntity.ok().build();
    }

    // DELETE a cart item by composite key.
    // Example URL: DELETE http://localhost:8080/api/cart-items?cartId=1&productId=2
    @DeleteMapping
    public ResponseEntity<Void> deleteCartItem(@RequestParam Long cartId, @RequestParam Long productId) {
        cartItemService.deleteCartItem(cartId, productId);
        return ResponseEntity.ok().build();
    }
}
