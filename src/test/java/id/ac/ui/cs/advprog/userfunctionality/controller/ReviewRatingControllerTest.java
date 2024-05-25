package id.ac.ui.cs.advprog.userfunctionality.controller;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.service.BookService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReviewRatingControllerTest {

    @Mock
    private ReviewRatingService reviewRatingService;

    @Mock
    private BookService bookService;

    @InjectMocks
    private ReviewRatingController reviewRatingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateReviewRating() {
        // Data dummy untuk ReviewRating dan Book
        ReviewRating reviewRating = new ReviewRating("user1", "Great book!", 9);
        Book book = new Book();
        book.setIsbn("1234567890");
        reviewRating.setBook(book);

        // DTO yang akan digunakan dalam pengujian
        ReviewRatingController.ReviewRatingDTO reviewRatingDTO = new ReviewRatingController.ReviewRatingDTO();
        reviewRatingDTO.setUsername(reviewRating.getUsername());
        reviewRatingDTO.setBookIsbn(book.getIsbn());
        reviewRatingDTO.setReview(reviewRating.getReview());
        reviewRatingDTO.setRating(reviewRating.getRating());

        // Mengatur mock untuk bookService dan reviewRatingService
        when(bookService.findByIsbn(book.getIsbn())).thenReturn(book); // Pastikan mengembalikan objek Book yang valid
        when(reviewRatingService.createReviewRating(any(ReviewRating.class))).thenReturn(reviewRating);

        // Memanggil controller dan memverifikasi hasil
        ResponseEntity<Object> response = reviewRatingController.createReviewRating(reviewRatingDTO);

        // Memastikan respons adalah CREATED dan mock method dipanggil
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(reviewRatingService, times(1)).createReviewRating(any(ReviewRating.class));
        verify(bookService, times(1)).findByIsbn(book.getIsbn());
    }

    @Test
    public void testReviewRatingList() {
        // Data dummy untuk daftar ReviewRating
        ReviewRating review1 = new ReviewRating("user1", "Great book!", 9);
        ReviewRating review2 = new ReviewRating("user2", "Not bad", 7);

        // Mengatur mock untuk reviewRatingService
        when(reviewRatingService.findAll()).thenReturn(Arrays.asList(review1, review2));

        // Memanggil controller dan memverifikasi hasil
        ResponseEntity<Object> response = reviewRatingController.reviewRatingList();

        // Memastikan respons adalah OK dan mock method dipanggil
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(reviewRatingService, times(1)).findAll();
    }

    @Test
    public void testGetAverageRatingByIsbn() {
        // Mengatur mock untuk reviewRatingService
        when(reviewRatingService.getAverageRatingByIsbn("1234567890")).thenReturn(8.0);

        // Memanggil controller dan memverifikasi hasil
        ResponseEntity<Object> response = reviewRatingController.getAverageRatingByIsbn("1234567890");

        // Memastikan respons adalah OK dan mock method dipanggil
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(8.0, response.getBody());
        verify(reviewRatingService, times(1)).getAverageRatingByIsbn("1234567890");
    }
}
