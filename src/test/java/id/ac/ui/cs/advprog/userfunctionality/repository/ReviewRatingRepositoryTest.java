package id.ac.ui.cs.advprog.userfunctionality.repository;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReviewRatingRepositoryTest {

    @Mock
    ReviewRatingRepository reviewRatingRepository;

    List<ReviewRating> reviewRatings;

    @BeforeEach
    void setUp() {
        reviewRatings = new ArrayList<>();

        ReviewRating reviewRating1 = new ReviewRating();
        reviewRating1.setReviewId("1");
        reviewRating1.setUsername("novrizair1");
        reviewRating1.setBookId("book1");
        reviewRating1.setReview("Good book");
        reviewRating1.setRating(4);
        reviewRatings.add(reviewRating1);

        ReviewRating reviewRating2 = new ReviewRating();
        reviewRating2.setReviewId("2");
        reviewRating2.setUsername("novrizair2");
        reviewRating2.setBookId("book2");
        reviewRating2.setReview("Excellent book");
        reviewRating2.setRating(5);
        reviewRatings.add(reviewRating2);
    }

    @Test
    void testCreate() {
        ReviewRating reviewRating = reviewRatings.getFirst();
        Mockito.when(reviewRatingRepository.save(reviewRating)).thenReturn(reviewRating);

        ReviewRating savedReviewRating = reviewRatingRepository.save(reviewRating);

        assertEquals(reviewRating, savedReviewRating);
    }

    @Test
    void testFindAll() {
        Mockito.when(reviewRatingRepository.findAll()).thenReturn((Iterable<ReviewRating>) reviewRatings.iterator());

        Iterator<ReviewRating> reviewRatingIterator = reviewRatingRepository.findAll().iterator();

        List<ReviewRating> retrievedReviewRatings = new ArrayList<>();
        reviewRatingIterator.forEachRemaining(retrievedReviewRatings::add);

        assertEquals(reviewRatings.size(), retrievedReviewRatings.size());
        assertTrue(retrievedReviewRatings.containsAll(reviewRatings));
    }

    @Test
    void testFindById() {
        ReviewRating reviewRating = reviewRatings.getFirst();
        Mockito.when(reviewRatingRepository.findById(reviewRating.getReviewId())).thenReturn(Optional.of(reviewRating));

        Optional<ReviewRating> retrievedReviewRating = reviewRatingRepository.findById(reviewRating.getReviewId());

        assertTrue(retrievedReviewRating.isPresent());
        assertEquals(reviewRating, retrievedReviewRating.get());
    }

    @Test
    void testDelete() {
        ReviewRating reviewRating = reviewRatings.getFirst();
        Mockito.doNothing().when(reviewRatingRepository).deleteById(reviewRating.getReviewId());

        reviewRatingRepository.deleteById(reviewRating.getReviewId());

        Mockito.verify(reviewRatingRepository, Mockito.times(1)).deleteById(reviewRating.getReviewId());
    }
}
