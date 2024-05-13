package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.repository.ReviewRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class ReviewRatingServiceImpl implements ReviewRatingService {

    @Autowired
    private ReviewRatingRepository reviewRatingRepository;

    @Override
    @Async
    public CompletableFuture<ReviewRating> createReviewRating(ReviewRating reviewRating) {
        Command createCommand = new CreateReviewRatingCommand(reviewRating, reviewRatingRepository);
        createCommand.execute();
        return CompletableFuture.completedFuture(reviewRating);
    }

    @Override
    @Async
    public CompletableFuture<List<ReviewRating>> findAll() {
        return CompletableFuture.supplyAsync(() -> {
            Iterable<ReviewRating> reviewRatingIterator = reviewRatingRepository.findAll();
            List<ReviewRating> allReviewRating = new ArrayList<>();
            reviewRatingIterator.forEach(allReviewRating::add);
            return allReviewRating;
        });
    }

    @Override
    @Async
    public CompletableFuture<Optional<ReviewRating>> findById(String reviewId) {
        return CompletableFuture.supplyAsync(() -> reviewRatingRepository.findById(reviewId));
    }

    @Override
    @Async
    public CompletableFuture<ReviewRating> updateReviewRating(String reviewId, ReviewRating updatedReviewRating) {
        Command updateCommand = new UpdateReviewRatingCommand(reviewId, updatedReviewRating, reviewRatingRepository);
        updateCommand.execute();
        return CompletableFuture.completedFuture(updatedReviewRating);
    }

    @Override
    @Async
    public CompletableFuture<Void> deleteReviewRating(String reviewId) {
        Command deleteCommand = new DeleteReviewRatingCommand(reviewId, reviewRatingRepository);
        deleteCommand.execute();
        return CompletableFuture.completedFuture(null);
    }

}
