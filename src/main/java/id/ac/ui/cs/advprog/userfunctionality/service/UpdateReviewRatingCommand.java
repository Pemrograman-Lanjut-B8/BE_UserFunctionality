package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.repository.ReviewRatingRepository;

public class UpdateReviewRatingCommand implements Command {
    private String reviewId;
    private ReviewRating updatedReviewRating;
    private ReviewRatingRepository reviewRatingRepository;

    public UpdateReviewRatingCommand(String reviewId, ReviewRating updatedReviewRating, ReviewRatingRepository reviewRatingRepository) {
        this.reviewId = reviewId;
        this.updatedReviewRating = updatedReviewRating;
        this.reviewRatingRepository = reviewRatingRepository;
    }

    @Override
    public void execute() {
        reviewRatingRepository.update(reviewId, updatedReviewRating);
    }
}