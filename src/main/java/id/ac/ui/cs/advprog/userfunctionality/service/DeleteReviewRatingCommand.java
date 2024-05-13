package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.repository.ReviewRatingRepository;

import java.util.Optional;

public class DeleteReviewRatingCommand implements Command {
    private final String reviewId;
    private ReviewRatingRepository reviewRatingRepository;

    public DeleteReviewRatingCommand(String reviewId, ReviewRatingRepository reviewRatingRepository) {
        this.reviewId = reviewId;
        this.reviewRatingRepository = reviewRatingRepository;
    }

    @Override
    public Optional<ReviewRating> execute() {
        Optional<ReviewRating> deletingReview = reviewRatingRepository.findById(reviewId);
        deletingReview.ifPresent(reviewRating -> {
            reviewRatingRepository.deleteById(reviewRating.getReviewId());
        });
        return deletingReview;

    }
}