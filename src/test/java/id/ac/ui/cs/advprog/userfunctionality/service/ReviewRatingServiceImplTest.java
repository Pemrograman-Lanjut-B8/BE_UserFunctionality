package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.repository.ReviewRatingRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReviewRatingServiceImplTest {

    @Mock
    private ReviewRatingRepository reviewRatingRepository;

    @InjectMocks
    private ReviewRatingServiceImpl reviewRatingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateReviewRating() {
        ReviewRating reviewRating = new ReviewRating("user1", "Great book!", 9);
        when(reviewRatingRepository.save(any(ReviewRating.class))).thenReturn(reviewRating);

        ReviewRating createdReview = reviewRatingService.createReviewRating(reviewRating);

        assertNotNull(createdReview);
        assertEquals("user1", createdReview.getUsername());
        verify(reviewRatingRepository, times(1)).save(reviewRating);
    }

    @Test
    public void testFindAll() {
        ReviewRating review1 = new ReviewRating("user1", "Great book!", 9);
        ReviewRating review2 = new ReviewRating("user2", "Not bad", 7);
        List<ReviewRating> reviewList = new ArrayList<>();
        reviewList.add(review1);
        reviewList.add(review2);

        when(reviewRatingRepository.findAll()).thenReturn(reviewList);

        List<ReviewRating> allReviews = reviewRatingService.findAll();

        assertEquals(2, allReviews.size());
        verify(reviewRatingRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        ReviewRating reviewRating = new ReviewRating("user1", "Great book!", 9);
        when(reviewRatingRepository.findById("1")).thenReturn(Optional.of(reviewRating));

        Optional<ReviewRating> foundReview = reviewRatingService.findById("1");

        assertTrue(foundReview.isPresent());
        assertEquals("user1", foundReview.get().getUsername());
        verify(reviewRatingRepository, times(1)).findById("1");
    }

    @Test
    public void testUpdateReviewRating() {
        ReviewRating existingReview = new ReviewRating("user1", "Great book!", 9);
        ReviewRating updatedReview = new ReviewRating("user1", "Good book!", 8);
        updatedReview.setReviewId(existingReview.getReviewId());

        when(reviewRatingRepository.findById(existingReview.getReviewId())).thenReturn(Optional.of(existingReview));
        when(reviewRatingRepository.save(any(ReviewRating.class))).thenReturn(updatedReview);

        ReviewRating result = reviewRatingService.updateReviewRating(existingReview.getReviewId(), updatedReview);

        assertNotNull(result);
        assertEquals("Good book!", result.getReview());
        verify(reviewRatingRepository, times(1)).findById(existingReview.getReviewId());
        verify(reviewRatingRepository, times(1)).save(updatedReview);
    }

    @Test
    public void testDeleteReviewRating() {
        ReviewRating reviewRating = new ReviewRating("user1", "Great book!", 9);
        when(reviewRatingRepository.findById("1")).thenReturn(Optional.of(reviewRating));

        reviewRatingService.deleteReviewRating("1");

        verify(reviewRatingRepository, times(1)).findById("1");
        verify(reviewRatingRepository, times(1)).deleteById("1");
    }

    @Test
    public void testGetAverageRatingByIsbn() {
        Book book = new Book();
        book.setIsbn("1234567890");

        ReviewRating review1 = new ReviewRating("user1", "Great book!", 9);
        review1.setBook(book);
        ReviewRating review2 = new ReviewRating("user2", "Not bad", 7);
        review2.setBook(book);
        List<ReviewRating> reviews = new ArrayList<>();
        reviews.add(review1);
        reviews.add(review2);

        when(reviewRatingRepository.findByBookIsbn("1234567890")).thenReturn(reviews);

        double averageRating = reviewRatingService.getAverageRatingByIsbn("1234567890");

        assertEquals(8.0, averageRating);
        verify(reviewRatingRepository, times(1)).findByBookIsbn("1234567890");
    }
}
