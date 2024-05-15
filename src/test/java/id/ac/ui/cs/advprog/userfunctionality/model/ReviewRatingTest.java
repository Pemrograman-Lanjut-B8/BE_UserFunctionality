package id.ac.ui.cs.advprog.userfunctionality.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewRatingTest {
    private ReviewRating reviewRating;

    @BeforeEach
    void setUp() {
        this.reviewRating = new ReviewRating();
        this.reviewRating.setReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.reviewRating.setUsername("novrizair");
        this.reviewRating.setBookId("123-4567891011");
        this.reviewRating.setReview("Mantap banget ini buku!");
        this.reviewRating.setRating(8);
    }

    @Test
    void testGetReviewId() {
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.reviewRating.getReviewId());
    }

    @Test
    void testGetUsername() {
        assertEquals("novrizair", this.reviewRating.getUsername());
    }

    @Test
    void testGetBookId() {
        assertEquals("123-4567891011", this.reviewRating.getBookId());
    }

    @Test
    void testGetReview() {
        assertEquals("Mantap banget ini buku!", this.reviewRating.getReview());
    }

    @Test
    void testGetRating() {
        assertEquals(8, this.reviewRating.getRating());
    }

//    @Test
//    public void testInvalidRatingLessThanZero() {
//        assertThrows(IllegalArgumentException.class, () -> new ReviewRating("novrizair", "000", "This is a review", -1));
//    }
//
//    @Test
//    public void testInvalidRatingMoreThanTen() {
//        assertThrows(IllegalArgumentException.class, () -> new ReviewRating("novrizair", "000", "This is a review", 11));
//    }

    @Test
    public void testEmptyReview() {
        assertThrows(IllegalArgumentException.class, () -> new ReviewRating("novrizair", "000", "", 5));
    }
}
