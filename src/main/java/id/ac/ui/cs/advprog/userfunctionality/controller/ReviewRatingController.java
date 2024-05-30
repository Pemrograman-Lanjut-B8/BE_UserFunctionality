package id.ac.ui.cs.advprog.userfunctionality.controller;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.service.BookService;
import id.ac.ui.cs.advprog.userfunctionality.service.ReviewRatingService;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class ReviewRatingController {

    @Autowired
    private ReviewRatingService reviewRatingService;

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<Object> createReviewRating(@RequestBody ReviewRatingDTO reviewRatingDTO) {
        try {
            Book book = bookService.findByIsbn(reviewRatingDTO.getBookIsbn());
            if (book == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
            }

            ReviewRating reviewRating = new ReviewRating();
            reviewRating.setUsername(reviewRatingDTO.getUsername());
            reviewRating.setReview(reviewRatingDTO.getReview());
            reviewRating.setRating(reviewRatingDTO.getRating());
            reviewRating.setBook(book);

            ReviewRating createdReviewRating = reviewRatingService.createReviewRating(reviewRating);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReviewRating);
        } catch (Exception ex) {

            System.out.println("Error in create review rating: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating review rating");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Object> reviewRatingList() {
        try {
            return ResponseEntity.ok(reviewRatingService.findAll());
        } catch (Exception ex) {

            System.out.println("Error in list review ratings: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error listing review ratings");
        }
    }

    @PutMapping("/edit/{reviewId}")
    public ResponseEntity<Object> editReviewRating(@PathVariable("reviewId") String reviewId, @RequestBody ReviewRatingDTO reviewRatingDTO) {
        try {

            Book book = bookService.findByIsbn(reviewRatingDTO.getBookIsbn());
            if (book == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
            }


            ReviewRating updatedReviewRating = new ReviewRating();
            updatedReviewRating.setReviewId(reviewId);
            updatedReviewRating.setUsername(reviewRatingDTO.getUsername());
            updatedReviewRating.setReview(reviewRatingDTO.getReview());
            updatedReviewRating.setRating(reviewRatingDTO.getRating());
            updatedReviewRating.setBook(book);


            ReviewRating result = reviewRatingService.updateReviewRating(reviewId, updatedReviewRating);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {

            System.out.println("Error in edit review rating: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error editing review rating");
        }
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<Object> deleteReviewRating(@PathVariable("reviewId") String reviewId) {
        try {
            reviewRatingService.deleteReviewRating(reviewId);

            return ResponseEntity.noContent().build();
        } catch (Exception ex) {

            System.out.println("Error in delete review rating: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/average/{isbn}")
    public ResponseEntity<Object> getAverageRatingByIsbn(@PathVariable("isbn") String isbn) {
        try {
            double averageRating = reviewRatingService.getAverageRatingByIsbn(isbn);
            return ResponseEntity.ok(averageRating);
        } catch (Exception ex) {

            System.out.println("Error in get average rating by isbn: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting average rating by isbn");
        }
    }

    @Data
    static class ReviewRatingDTO {
        private String username;
        private String bookIsbn;
        private String review;
        private int rating;
    }
}