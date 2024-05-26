package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.repository.ReviewRatingRepository;
import id.ac.ui.cs.advprog.userfunctionality.service.command.Command;
import id.ac.ui.cs.advprog.userfunctionality.service.command.CreateReviewRatingCommand;
import id.ac.ui.cs.advprog.userfunctionality.service.command.DeleteReviewRatingCommand;
import id.ac.ui.cs.advprog.userfunctionality.service.command.UpdateReviewRatingCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class ReviewRatingServiceImpl implements ReviewRatingService {

    @Autowired
    private ReviewRatingRepository reviewRatingRepository;

    @Override
    public ReviewRating createReviewRating(ReviewRating reviewRating) {
        Command createCommand = new CreateReviewRatingCommand(reviewRating, reviewRatingRepository);
        createCommand.execute();
        return reviewRating;
    }

    @Override
    public List<ReviewRating> findAll() {
        Iterable<ReviewRating> reviewRatingIterator = reviewRatingRepository.findAll();
        List<ReviewRating> allReviewRating = new ArrayList<>();
        reviewRatingIterator.forEach(allReviewRating::add);
        return allReviewRating;
    }

    @Override
    public Optional<ReviewRating> findById(String reviewId) {
        return reviewRatingRepository.findById(reviewId);
    }

    @Override
    public ReviewRating updateReviewRating(String reviewId, ReviewRating updatedReviewRating) {
        Command updateCommand = new UpdateReviewRatingCommand(reviewId, updatedReviewRating, reviewRatingRepository);
        updateCommand.execute();
        return updatedReviewRating;
    }

    @Override
    public void deleteReviewRating(String reviewId) {
        Command deleteCommand = new DeleteReviewRatingCommand(reviewId, reviewRatingRepository);
        deleteCommand.execute();
    }

    @Override
    public double getAverageRatingByIsbn(String isbn) {
        List<ReviewRating> reviews = reviewRatingRepository.findByBookIsbn(isbn);
        return reviews.stream()
                .mapToInt(ReviewRating::getRating)
                .average()
                .orElse(0.0);
    }
}
