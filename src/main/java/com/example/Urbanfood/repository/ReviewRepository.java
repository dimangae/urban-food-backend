package com.example.Urbanfood.repository;
import com.example.Urbanfood.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {

    // Find reviews for a specific product
    List<Review> findByProductId(Long productId);

    // Find reviews by customer
    List<Review> findByCustomerId(Long customerId);
}