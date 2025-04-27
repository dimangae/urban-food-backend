package com.example.Urbanfood.service;

import com.example.Urbanfood.entity.Orders;
import com.example.Urbanfood.entity.Review;
import com.example.Urbanfood.repository.ReviewRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // Add a new review
    public Review addReview(Long productId, Long customerId, String reviewText, double rating) {
        Review review = new Review(productId, customerId, reviewText, rating, new Date());
        return reviewRepository.save(review);
    }

    // Get reviews for a product
    public List<Review> getReviewsByProduct(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    // Get reviews by customer
    public List<Review> getReviewsByCustomer(Long customerId) {
        return reviewRepository.findByCustomerId(customerId);
    }

    // Delete a review
    public void deleteReview(String reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found with id: " + reviewId);
        }
        reviewRepository.deleteById(reviewId);
    }

    @Transactional
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Get reviews by id
    public Optional<Review> getReviewById(String id) {
        return reviewRepository.findById(id);
    }
}
