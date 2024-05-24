package id.ac.ui.cs.advprog.userfunctionality.controller;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.service.ReviewRatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ReviewRatingControllerTest {

    @Mock
    private ReviewRatingService reviewRatingService;

    @InjectMocks
    private ReviewRatingController reviewRatingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateReviewRating() {
        ReviewRating reviewRating = new ReviewRating("user1", "Great book!", 9);
        ReviewRatingController.ReviewRatingDTO reviewRatingDTO = new ReviewRatingController.ReviewRatingDTO();
        reviewRatingDTO.setUsername(reviewRating.getUsername());
        reviewRatingDTO.setReview(reviewRating.getReview());
        reviewRatingDTO.setRating(reviewRating.getRating());

        when(reviewRatingService.createReviewRating(any(ReviewRating.class))).thenReturn(reviewRating);

        ResponseEntity<Object> response = reviewRatingController.createReviewRating(reviewRatingDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(reviewRatingService, times(1)).createReviewRating(any(ReviewRating.class));
    }


    @Test
    public void testReviewRatingList() {
        ReviewRating review1 = new ReviewRating("user1", "Great book!", 9);
        ReviewRating review2 = new ReviewRating("user2", "Not bad", 7);

        when(reviewRatingService.findAll()).thenReturn(Arrays.asList(review1, review2));

        ResponseEntity<Object> response = reviewRatingController.reviewRatingList();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(reviewRatingService, times(1)).findAll();
    }

    @Test
    public void testGetAverageRatingByIsbn() {
        when(reviewRatingService.getAverageRatingByIsbn("1234567890")).thenReturn(8.0);

        ResponseEntity<Object> response = reviewRatingController.getAverageRatingByIsbn("1234567890");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(8.0, response.getBody());
        verify(reviewRatingService, times(1)).getAverageRatingByIsbn("1234567890");
    }
}
