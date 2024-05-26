package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.dto.BookTopDTO;
import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;

import java.util.List;
import java.util.Optional;

public interface ReviewRatingService {
    ReviewRating createReviewRating(ReviewRating reviewRating);
    List<ReviewRating> findAll();
    Optional<ReviewRating> findById(String reviewId);
    ReviewRating updateReviewRating(String reviewId, ReviewRating updatedReviewRating);
    void deleteReviewRating(String reviewId);
    double getAverageRatingByIsbn(String isbn);
}