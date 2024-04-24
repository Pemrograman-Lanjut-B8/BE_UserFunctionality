package id.ac.ui.cs.advprog.userfunctionality.repository;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewRatingRepositoryTest {

    private ReviewRatingRepository reviewRatingRepository;

    @BeforeEach
    void setUp() {
        reviewRatingRepository = new ReviewRatingRepository();
    }

    @Test
    void testCreateAndFind() {
        ReviewRating reviewRating = new ReviewRating();
        reviewRating.setReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        reviewRating.setUsername("novrizair");
        reviewRating.setBookId("123-4567891011");
        reviewRating.setReview("Mantap banget ini buku!");
        reviewRating.setRating(8);
        reviewRatingRepository.create(reviewRating);

        Iterator<ReviewRating> reviewRatingIterator = reviewRatingRepository.findAll();
        assertTrue(reviewRatingIterator.hasNext());
        ReviewRating savedReviewRating = reviewRatingIterator.next();
        assertEquals(reviewRating.getReviewId(), savedReviewRating.getReviewId());
        assertEquals(reviewRating.getUsername(), savedReviewRating.getUsername());
        assertEquals(reviewRating.getBookId(), savedReviewRating.getBookId());
        assertEquals(reviewRating.getReview(), savedReviewRating.getReview());
        assertEquals(reviewRating.getRating(), savedReviewRating.getRating());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<ReviewRating> reviewRatingIterator = reviewRatingRepository.findAll();
        assertFalse(reviewRatingIterator.hasNext());
    }

    @Test
    void testDeleteReviewRating() {
        ReviewRating reviewRating = new ReviewRating();
        reviewRating.setReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        reviewRating.setUsername("novrizair");
        reviewRating.setBookId("123-4567891011");
        reviewRating.setReview("Mantap banget ini buku!");
        reviewRating.setRating(8);
        reviewRatingRepository.create(reviewRating);

        reviewRatingRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertFalse(reviewRatingRepository.findAll().hasNext());
    }

    @Test
    void testUpdateReviewRating() {
        ReviewRating reviewRating = new ReviewRating();
        reviewRating.setReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        reviewRating.setUsername("novrizair");
        reviewRating.setBookId("123-4567891011");
        reviewRating.setReview("Mantap banget ini buku!");
        reviewRating.setRating(8);
        reviewRatingRepository.create(reviewRating);

        ReviewRating updatedReviewRating = new ReviewRating();
        updatedReviewRating.setReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        updatedReviewRating.setUsername("novrizair");
        updatedReviewRating.setBookId("123-4567891011");
        updatedReviewRating.setReview("Sangat luar biasa!");
        updatedReviewRating.setRating(10);
        reviewRatingRepository.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedReviewRating);

        ReviewRating savedReviewRating = reviewRatingRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6").orElse(null);
        assertNotNull(savedReviewRating);
        assertEquals(updatedReviewRating.getReview(), savedReviewRating.getReview());
        assertEquals(updatedReviewRating.getRating(), savedReviewRating.getRating());
    }
}
