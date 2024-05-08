package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface ReviewRatingService {
    CompletableFuture<ReviewRating> createReviewRating(ReviewRating reviewRating);
    CompletableFuture<List<ReviewRating>> findAll();
    CompletableFuture<Optional<ReviewRating>> findById(String reviewId);
    CompletableFuture<ReviewRating> updateReviewRating(String reviewId, ReviewRating updatedReviewRating);
    CompletableFuture<Void> deleteReviewRating(String reviewId);
}
