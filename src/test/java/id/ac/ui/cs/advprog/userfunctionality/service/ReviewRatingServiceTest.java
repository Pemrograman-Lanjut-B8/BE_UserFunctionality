package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.repository.ReviewRatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReviewRatingServiceTest {

    @Mock
    private ReviewRatingRepository reviewRatingRepository;

    @Mock
    private BookService bookService;

    @InjectMocks
    private ReviewRatingServiceImpl reviewRatingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateReviewRating() {
        ReviewRating reviewRating = new ReviewRating("user1", "Great book!", 9);
        Book book = new Book();
        book.setIsbn("1234567890");

        when(bookService.findByIsbn("1234567890")).thenReturn(book);
        when(reviewRatingRepository.save(any(ReviewRating.class))).thenReturn(reviewRating);

        ReviewRating createdReview = reviewRatingService.createReviewRating(reviewRating);

        assertEquals("user1", createdReview.getUsername());
        verify(reviewRatingRepository, times(1)).save(reviewRating);
    }

    @Test
    public void testFindAll() {
        ReviewRating review1 = new ReviewRating("user1", "Great book!", 9);
        ReviewRating review2 = new ReviewRating("user2", "Not bad", 7);

        when(reviewRatingRepository.findAll()).thenReturn(Arrays.asList(review1, review2));

        assertEquals(2, reviewRatingService.findAll().size());
        verify(reviewRatingRepository, times(1)).findAll();
    }

    @Test
    public void testGetAverageRatingByIsbn() {
        Book book = new Book();
        book.setIsbn("1234567890");

        ReviewRating review1 = new ReviewRating("user1", "Great book!", 9);
        review1.setBook(book);
        ReviewRating review2 = new ReviewRating("user2", "Not bad", 7);
        review2.setBook(book);

        when(reviewRatingRepository.findByBookIsbn("1234567890")).thenReturn(Arrays.asList(review1, review2));

        double averageRating = reviewRatingService.getAverageRatingByIsbn("1234567890");

        assertEquals(8.0, averageRating);
        verify(reviewRatingRepository, times(1)).findByBookIsbn("1234567890");
    }
}
