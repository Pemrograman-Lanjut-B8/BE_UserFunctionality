package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;

import java.util.List;
import java.util.Optional;

public interface ReviewRatingService {
    ReviewRating create(ReviewRating reviewRating);
    List<ReviewRating> findAll();
    Optional<ReviewRating> findById(String reviewId);
    ReviewRating update(String reviewId, ReviewRating updatedReviewRating);
    boolean delete(String reviewId);
}