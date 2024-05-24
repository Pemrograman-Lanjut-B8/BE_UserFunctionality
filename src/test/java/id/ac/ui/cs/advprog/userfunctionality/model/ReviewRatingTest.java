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
    void testGetReview() {
        assertEquals("Mantap banget ini buku!", this.reviewRating.getReview());
    }

    @Test
    void testGetRating() {
        assertEquals(8, this.reviewRating.getRating());
    }

}
