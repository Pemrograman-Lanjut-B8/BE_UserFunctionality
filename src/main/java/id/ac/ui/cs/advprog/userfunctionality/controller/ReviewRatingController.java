package id.ac.ui.cs.advprog.userfunctionality.controller;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.service.ReviewRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewRatingController {

    @Autowired
    private ReviewRatingService reviewRatingService;

    @PostMapping("/create")
    public ResponseEntity<?> createReviewRating(@RequestBody ReviewRating reviewRating) {
        ReviewRating createdReviewRating = reviewRatingService.createReviewRating(reviewRating);
        return ResponseEntity.ok(createdReviewRating);
    }

    @GetMapping("/list")
    public ResponseEntity<?> reviewRatingList() {
        List<ReviewRating> allReviewRatings = reviewRatingService.findAll();
        return ResponseEntity.ok(allReviewRatings);
    }

    @PutMapping("/edit/{reviewId}")
    public ResponseEntity<?> editReviewRating(@PathVariable("reviewId") String reviewId, @RequestBody ReviewRating reviewRating) {
        ReviewRating updatedReviewRating = reviewRatingService.updateReviewRating(reviewId, reviewRating);
        return ResponseEntity.ok(updatedReviewRating);
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<?> deleteReviewRating(@PathVariable("reviewId") String reviewId) {
        reviewRatingService.deleteReviewRating(reviewId);
        return ResponseEntity.ok().build();
    }
}
