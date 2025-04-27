package com.example.Urbanfood.controller;
import com.example.Urbanfood.entity.Orders;
import com.example.Urbanfood.entity.Product;
import com.example.Urbanfood.entity.Review;
import com.example.Urbanfood.repository.ReviewRepository;
import com.example.Urbanfood.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Add a new review
    @PostMapping("/add")
    public ResponseEntity<Review> addReview(@RequestParam Long productId,
                                            @RequestParam Long customerId,
                                            @RequestParam String reviewText,
                                            @RequestParam double rating) {
        Review review = reviewService.addReview(productId, customerId, reviewText, rating);
        return ResponseEntity.ok(review);
    }

    // Get all reviews for a product
    @GetMapping("/product")
    public ResponseEntity<List<Review>> getReviewsByProduct(@RequestParam Long productId) {
        List<Review> reviews = reviewService.getReviewsByProduct(productId);
        return ResponseEntity.ok(reviews);
    }

    // Get reviews by customer
    @GetMapping("/customer")
    public ResponseEntity<List<Review>> getReviewsByCustomer(@RequestParam Long customerId) {
        List<Review> reviews = reviewService.getReviewsByCustomer(customerId);
        return ResponseEntity.ok(reviews);
    }

    // Delete a review
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteReview(@RequestParam String reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

    // GET all reviews
    @GetMapping
    public ResponseEntity<List<Review>> getAllOrders() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    // GET reviews by ID
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable String id) {
        Optional<Review> review = reviewService.getReviewById(id);
        return review.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
