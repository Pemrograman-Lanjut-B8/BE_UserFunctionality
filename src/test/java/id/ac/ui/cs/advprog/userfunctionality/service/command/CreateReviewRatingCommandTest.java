package id.ac.ui.cs.advprog.userfunctionality.service.command;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.repository.ReviewRatingRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateReviewRatingCommandTest {

    @Mock
    private ReviewRatingRepository reviewRatingRepository;

    @InjectMocks
    private CreateReviewRatingCommand createReviewRatingCommand;

    @Test
    void testExecute() {
        ReviewRating reviewRating = new ReviewRating();
        reviewRating.setReviewId("1");
        reviewRating.setUsername("novrizair1");
        reviewRating.setBookId("book1");
        reviewRating.setReview("Good book");
        reviewRating.setRating(4);

        when(reviewRatingRepository.save(reviewRating)).thenReturn(reviewRating);

        Optional<ReviewRating> result = createReviewRatingCommand.execute();

        assertEquals(Optional.of(reviewRating), result);
        verify(reviewRatingRepository, times(1)).save(reviewRating);
    }
}
