package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.repository.ReviewRatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewRatingServiceImplTest {

    @Mock
    private ReviewRatingRepository reviewRatingRepository;

    @InjectMocks
    private ReviewRatingServiceImpl reviewRatingService;

    private ReviewRating reviewRating1;
    private ReviewRating reviewRating2;

    @BeforeEach
    void setUp() {
        reviewRating1 = new ReviewRating();
        reviewRating1.setReviewId("1");
        reviewRating1.setUsername("user1");
        reviewRating1.setBookId("book1");
        reviewRating1.setReview("Good book");
        reviewRating1.setRating(4);

        reviewRating2 = new ReviewRating();
        reviewRating2.setReviewId("2");
        reviewRating2.setUsername("user2");
        reviewRating2.setBookId("book2");
        reviewRating2.setReview("Excellent book");
        reviewRating2.setRating(5);
    }

    @Test
    void testCreateReviewRating() {
        when(reviewRatingRepository.create(any(ReviewRating.class))).thenReturn(reviewRating1);
        CompletableFuture<ReviewRating> future = reviewRatingService.createReviewRating(reviewRating1);
        assertEquals(reviewRating1, future.join());
        verify(reviewRatingRepository, times(1)).create(reviewRating1);
    }

    @Test
    void testFindAll() {
        List<ReviewRating> reviewRatings = new ArrayList<>();
        reviewRatings.add(reviewRating1);
        reviewRatings.add(reviewRating2);

        when(reviewRatingRepository.findAll()).thenReturn(reviewRatings.iterator());

        CompletableFuture<List<ReviewRating>> future = reviewRatingService.findAll();
        assertEquals(reviewRatings, future.join());
        verify(reviewRatingRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(reviewRatingRepository.findById("1")).thenReturn(Optional.of(reviewRating1));

        CompletableFuture<Optional<ReviewRating>> future = reviewRatingService.findById("1");
        assertEquals(Optional.of(reviewRating1), future.join());
        verify(reviewRatingRepository, times(1)).findById("1");
    }

    @Test
    void testUpdateReviewRating() {
        when(reviewRatingRepository.findById("1")).thenReturn(Optional.of(reviewRating1));
        when(reviewRatingRepository.update("1", reviewRating1)).thenReturn(reviewRating1);

        CompletableFuture<ReviewRating> future = reviewRatingService.updateReviewRating("1", reviewRating1);
        assertEquals(reviewRating1, future.join());
        verify(reviewRatingRepository, times(1)).findById("1");
        verify(reviewRatingRepository, times(1)).update("1", reviewRating1);
    }

    @Test
    void testDeleteReviewRating() {
        doNothing().when(reviewRatingRepository).delete("1");

        CompletableFuture<Void> future = reviewRatingService.deleteReviewRating("1");
        future.join();
        verify(reviewRatingRepository, times(1)).delete("1");
    }
}
