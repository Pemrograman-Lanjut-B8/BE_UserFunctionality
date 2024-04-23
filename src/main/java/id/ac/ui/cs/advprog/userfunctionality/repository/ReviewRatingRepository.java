package id.ac.ui.cs.advprog.userfunctionality.repository;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class ReviewRatingRepository {
    private List<ReviewRating> reviewRatingData = new ArrayList<>();

    public ReviewRating create(ReviewRating reviewRating) {
        reviewRatingData.add(reviewRating);
        return reviewRating;
    }

    public Iterator<ReviewRating> findAll() {
        return reviewRatingData.iterator();
    }

    public Optional<ReviewRating> findById(String reviewId) {
        return reviewRatingData.stream()
                .filter(reviewRating -> reviewRating.getReviewId().equals(reviewId))
                .findFirst();
    }

    public ReviewRating update(String reviewId, ReviewRating updatedReviewRating) {
        for (int i = 0; i < reviewRatingData.size(); i++) {
            ReviewRating reviewRating = reviewRatingData.get(i);
            if (reviewRating.getReviewId().equals(reviewId)) {
                // Update review and rating
                reviewRating.setReview(updatedReviewRating.getReview());
                reviewRating.setRating(updatedReviewRating.getRating());
                return reviewRating;
            }
        }
        return null;
    }

    public boolean delete(String reviewId) {
        Iterator<ReviewRating> iterator = reviewRatingData.iterator();
        while (iterator.hasNext()) {
            ReviewRating reviewRating = iterator.next();
            if (reviewRating.getReviewId().equals(reviewId)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
