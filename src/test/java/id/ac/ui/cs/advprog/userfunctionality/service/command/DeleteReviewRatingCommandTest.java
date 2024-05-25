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
//class DeleteReviewRatingCommandTest {
//
//    @Mock
//    private ReviewRatingRepository reviewRatingRepository;
//
//    @InjectMocks
//    private DeleteReviewRatingCommand deleteReviewRatingCommand;
//
//    @Test
//    void testExecute() {
//        String reviewId = "1";
//        ReviewRating reviewRating = new ReviewRating();
//        reviewRating.setReviewId(reviewId);
//        reviewRating.setUsername("novrizair1");
//        reviewRating.setBookId("book1");
//        reviewRating.setReview("Good book");
//        reviewRating.setRating(4);
//
//        when(reviewRatingRepository.findById(reviewId)).thenReturn(Optional.of(reviewRating));
//
//        Optional<ReviewRating> result = deleteReviewRatingCommand.execute();
//
//        assertEquals(Optional.of(reviewRating), result);
//        verify(reviewRatingRepository, times(1)).findById(reviewId);
//        verify(reviewRatingRepository, times(1)).deleteById(reviewId);
//    }
//}
