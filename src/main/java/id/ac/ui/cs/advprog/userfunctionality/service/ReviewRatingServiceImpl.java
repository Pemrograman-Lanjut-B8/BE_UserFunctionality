package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.repository.ReviewRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewRatingServiceImpl implements ReviewRatingService {

    @Autowired
    private ReviewRatingRepository reviewRatingRepository;

    @Override
    public ReviewRating create(ReviewRating reviewRating) {
        return reviewRatingRepository.create(reviewRating);
    }

    @Override
    public List<ReviewRating> findAll() {
        return reviewRatingRepository.findAll();
    }

    @Override
    public Optional<ReviewRating> findById(String reviewId) {
        return reviewRatingRepository.findById(reviewId);
    }

    @Override
    public ReviewRating update(String reviewId, ReviewRating updatedReviewRating) {
        Optional<ReviewRating> existingReviewRating = reviewRatingRepository.findById(reviewId);
        if (existingReviewRating.isPresent()) {
            ReviewRating reviewRating = existingReviewRating.get();
            reviewRating.setReview(updatedReviewRating.getReview());
            reviewRating.setRating(updatedReviewRating.getRating());
            return reviewRatingRepository.update(reviewId, reviewRating);
        }
        return null;
    }

    @Override
    public boolean delete(String reviewId) {
        return reviewRatingRepository.delete(reviewId);
    }
}