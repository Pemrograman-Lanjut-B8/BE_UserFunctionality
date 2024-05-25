package id.ac.ui.cs.advprog.userfunctionality.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewRatingTest {

    @Test
    public void testDefaultConstructor() {
        ReviewRating reviewRating = new ReviewRating();
        assertNotNull(reviewRating.getReviewId());
        assertNotNull(reviewRating.getDateTime());
    }

    @Test
    public void testParameterizedConstructor() {
        String username = "user1";
        String review = "Great book!";
        int rating = 9;

        ReviewRating reviewRating = new ReviewRating(username, review, rating);

        assertEquals(username, reviewRating.getUsername());
        assertEquals(review, reviewRating.getReview());
        assertEquals(rating, reviewRating.getRating());
        assertNotNull(reviewRating.getReviewId());
        assertNotNull(reviewRating.getDateTime());
    }

    @Test
    public void testSetReviewId() {
        ReviewRating reviewRating = new ReviewRating();
        String reviewId = UUID.randomUUID().toString();
        reviewRating.setReviewId(reviewId);
        assertEquals(reviewId, reviewRating.getReviewId());
    }

    @Test
    public void testSetReviewValid() {
        ReviewRating reviewRating = new ReviewRating();
        String review = "Great book!";
        reviewRating.setReview(review);
        assertEquals(review, reviewRating.getReview());
    }

    @Test
    public void testSetReviewInvalid() {
        ReviewRating reviewRating = new ReviewRating();
        assertThrows(IllegalArgumentException.class, () -> reviewRating.setReview(""));
    }

    @Test
    public void testSetRatingValid() {
        ReviewRating reviewRating = new ReviewRating();
        reviewRating.setRating(5);
        assertEquals(5, reviewRating.getRating());
    }

    @Test
    public void testSetRatingBelowMinimum() {
        ReviewRating reviewRating = new ReviewRating();
        reviewRating.setRating(-1);
        assertEquals(0, reviewRating.getRating());
    }

    @Test
    public void testSetRatingAboveMaximum() {
        ReviewRating reviewRating = new ReviewRating();
        reviewRating.setRating(11);
        assertEquals(10, reviewRating.getRating());
    }

    @Test
    public void testSetBook() {
        ReviewRating reviewRating = new ReviewRating();
        Book book = new Book();
        book.setIsbn("1234567890");
        reviewRating.setBook(book);
        assertEquals(book, reviewRating.getBook());
    }

    @Test
    public void testGetDateTime() {
        ReviewRating reviewRating = new ReviewRating();
        LocalDateTime dateTime = reviewRating.getDateTime();
        assertNotNull(dateTime);
    }
}
