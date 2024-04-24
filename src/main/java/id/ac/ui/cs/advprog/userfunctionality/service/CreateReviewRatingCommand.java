package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.repository.ReviewRatingRepository;

public class CreateReviewRatingCommand implements Command {
    private ReviewRating reviewRating;
    private ReviewRatingRepository reviewRatingRepository;

    public CreateReviewRatingCommand(ReviewRating reviewRating, ReviewRatingRepository reviewRatingRepository) {
        this.reviewRating = reviewRating;
        this.reviewRatingRepository = reviewRatingRepository;
    }

    @Override
    public void execute() {
        reviewRatingRepository.create(reviewRating);
    }
}
