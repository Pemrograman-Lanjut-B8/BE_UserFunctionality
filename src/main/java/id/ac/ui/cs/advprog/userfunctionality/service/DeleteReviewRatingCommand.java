package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.repository.ReviewRatingRepository;

public class DeleteReviewRatingCommand implements Command {
    private String reviewId;
    private ReviewRatingRepository reviewRatingRepository;

    public DeleteReviewRatingCommand(String reviewId, ReviewRatingRepository reviewRatingRepository) {
        this.reviewId = reviewId;
        this.reviewRatingRepository = reviewRatingRepository;
    }

    @Override
    public void execute() {
        reviewRatingRepository.delete(reviewId);
    }
}