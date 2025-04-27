package com.example.Urbanfood.controller;

import com.example.Urbanfood.entity.Product;
import com.example.Urbanfood.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return (product != null)
                ? ResponseEntity.ok(product)
                : ResponseEntity.notFound().build();
    }
    //GET all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(products);
    }

    // POST to insert a new product
    @PostMapping
    public ResponseEntity<Long> insertProduct(
            @RequestParam Long supplierId,
            @RequestParam String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String category,
            @RequestParam BigDecimal price,
            @RequestParam Integer stockQuantity,
            @RequestParam String imageUrl) {
        Long newId = productService.insertProduct(supplierId, name, description, category, price, stockQuantity, imageUrl);
        return ResponseEntity.ok(newId);
    }

    // PUT to update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable Long id,
            @RequestParam Long supplierId,
            @RequestParam String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String category,
            @RequestParam BigDecimal price,
            @RequestParam Integer stockQuantity,
            @RequestParam String imageUrl) {
        productService.updateProduct(id, supplierId, name, description, category, price, stockQuantity, imageUrl);
        return ResponseEntity.ok().build();
    }

    // DELETE a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
