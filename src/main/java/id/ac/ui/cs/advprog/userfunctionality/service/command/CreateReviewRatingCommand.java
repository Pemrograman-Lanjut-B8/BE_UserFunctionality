package id.ac.ui.cs.advprog.userfunctionality.service.command;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.repository.ReviewRatingRepository;
import id.ac.ui.cs.advprog.userfunctionality.service.command.Command;

import java.util.Optional;

public class CreateReviewRatingCommand implements Command {
    private ReviewRating reviewRating;
    private ReviewRatingRepository reviewRatingRepository;

    public CreateReviewRatingCommand(ReviewRating reviewRating, ReviewRatingRepository reviewRatingRepository) {
        this.reviewRating = reviewRating;
        this.reviewRatingRepository = reviewRatingRepository;
    }

    @Override
    public Optional<ReviewRating> execute() {
        return Optional.of(reviewRatingRepository.save(reviewRating));
    }
}
