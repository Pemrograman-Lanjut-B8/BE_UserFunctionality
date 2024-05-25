//package id.ac.ui.cs.advprog.userfunctionality.service.command;
//
//import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
//import id.ac.ui.cs.advprog.userfunctionality.repository.ReviewRatingRepository;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class UpdateReviewRatingCommandTest {
//
//    @Mock
//    private ReviewRatingRepository reviewRatingRepository;
//
//    @InjectMocks
//    private UpdateReviewRatingCommand updateReviewRatingCommand;
//
//    @Test
//    void testExecute() {
//        String reviewId = "1";
//        ReviewRating updatedReviewRating = new ReviewRating();
//        updatedReviewRating.setReviewId(reviewId);
//        updatedReviewRating.setUsername("novrizair1");
//        updatedReviewRating.setBookId("book1");
//        updatedReviewRating.setReview("Good book");
//        updatedReviewRating.setRating(4);
//
//        ReviewRating existingReviewRating = new ReviewRating();
//        existingReviewRating.setReviewId(reviewId);
//        existingReviewRating.setUsername("novrizair1");
//        existingReviewRating.setBookId("book1");
//        existingReviewRating.setReview("Old book");
//        existingReviewRating.setRating(3);
//
//        when(reviewRatingRepository.findById(reviewId)).thenReturn(Optional.of(existingReviewRating));
//
//        Optional<ReviewRating> result = updateReviewRatingCommand.execute();
//
//        assertEquals(Optional.of(existingReviewRating), result);
//        verify(reviewRatingRepository, times(1)).findById(reviewId);
//        verify(reviewRatingRepository, times(1)).save(updatedReviewRating);
//    }
//}
