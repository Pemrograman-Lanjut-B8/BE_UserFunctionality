package id.ac.ui.cs.advprog.userfunctionality.service.command;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.repository.ReviewRatingRepository;
import id.ac.ui.cs.advprog.userfunctionality.service.command.Command;

import java.util.Optional;

public class UpdateReviewRatingCommand implements Command {
    private final String reviewId;
    private ReviewRating updatedReviewRating;
    private ReviewRatingRepository reviewRatingRepository;

    public UpdateReviewRatingCommand(String reviewId, ReviewRating updatedReviewRating, ReviewRatingRepository reviewRatingRepository) {
        this.reviewId = reviewId;
        this.updatedReviewRating = updatedReviewRating;
        this.reviewRatingRepository = reviewRatingRepository;
    }

    @Override
    public Optional<ReviewRating> execute() {
        return reviewRatingRepository.findById(updatedReviewRating.getReviewId())
                .map(existsReviewRating -> {
                    reviewRatingRepository.save(updatedReviewRating);
                    return existsReviewRating;
                });
    }

}

