package id.ac.ui.cs.advprog.userfunctionality.repository;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class ReviewRatingRepositoryTest {

    @Autowired
    private ReviewRatingRepository reviewRatingRepository;

    @Test
    public void testFindByBookIsbn() {
        Book book = new Book();
        book.setIsbn("1234567890");

        ReviewRating review1 = new ReviewRating("user1", "Great book!", 9);
        review1.setBook(book);
        ReviewRating review2 = new ReviewRating("user2", "Not bad", 7);
        review2.setBook(book);

        reviewRatingRepository.save(review1);
        reviewRatingRepository.save(review2);

        List<ReviewRating> reviews = reviewRatingRepository.findByBookIsbn("1234567890");

        assertNotNull(reviews);
        assertEquals(2, reviews.size());
        assertEquals("user1", reviews.get(0).getUsername());
        assertEquals("user2", reviews.get(1).getUsername());
    }
}
