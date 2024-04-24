package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.repository.ReviewRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class ReviewRatingServiceImpl implements ReviewRatingService {

    @Autowired
    private ReviewRatingRepository reviewRatingRepository;

    @Override
    public void createReviewRating(ReviewRating reviewRating) {
        Command createCommand = new CreateReviewRatingCommand(reviewRating, reviewRatingRepository);
        createCommand.execute();
    }
    @Override
    public List<ReviewRating> findAll() {
        Iterator<ReviewRating> reviewRatingIterator = reviewRatingRepository.findAll();
        List<ReviewRating> allReviewRating = new ArrayList<>();
        reviewRatingIterator.forEachRemaining(allReviewRating::add);
        return allReviewRating;
    }

    @Override
    public Optional<ReviewRating> findById(String reviewId) {
        return reviewRatingRepository.findById(reviewId);
    }

    @Override
    public void updateReviewRating(String reviewId, ReviewRating updatedReviewRating) {
        Command updateCommand = new UpdateReviewRatingCommand(reviewId, updatedReviewRating, reviewRatingRepository);
        updateCommand.execute();
    }

    @Override
    public void deleteReviewRating(String reviewId) {
        Command deleteCommand = new DeleteReviewRatingCommand(reviewId, reviewRatingRepository);
        deleteCommand.execute();    }
}