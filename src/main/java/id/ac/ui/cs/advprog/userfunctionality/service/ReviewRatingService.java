package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;

import java.util.List;
import java.util.Optional;

public interface ReviewRatingService {
    void createReviewRating(ReviewRating reviewRating);
    List<ReviewRating> findAll();
    Optional<ReviewRating> findById(String reviewId);
    void updateReviewRating(String reviewId, ReviewRating updatedReviewRating);
    void deleteReviewRating(String reviewId);
}