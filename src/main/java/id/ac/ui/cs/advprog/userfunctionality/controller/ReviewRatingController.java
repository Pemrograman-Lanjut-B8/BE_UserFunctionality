package id.ac.ui.cs.advprog.userfunctionality.controller;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.service.ReviewRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/review")
public class ReviewRatingController {

    @Autowired
    private ReviewRatingService reviewRatingService;

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<?>> createReviewRating(@RequestBody ReviewRating reviewRating) {
        return reviewRatingService.createReviewRating(reviewRating)
                .thenApply(createdReviewRating -> ResponseEntity.ok(createdReviewRating));
    }

    @GetMapping("/list")
    public CompletableFuture<ResponseEntity<?>> reviewRatingList() {
        return reviewRatingService.findAll()
                .thenApply(allReviewRatings -> ResponseEntity.ok(allReviewRatings));
    }

    @PutMapping("/edit/{reviewId}")
    public CompletableFuture<ResponseEntity<?>> editReviewRating(@PathVariable("reviewId") String reviewId, @RequestBody ReviewRating reviewRating) {
        return reviewRatingService.updateReviewRating(reviewId, reviewRating)
                .thenApply(updatedReviewRating -> ResponseEntity.ok(updatedReviewRating));
    }

    @DeleteMapping("/delete/{reviewId}")
    public CompletableFuture<ResponseEntity<Void>> deleteReviewRating(@PathVariable("reviewId") String reviewId) {
        return reviewRatingService.deleteReviewRating(reviewId)
                .thenApply(voidResult -> ResponseEntity.noContent().build());
    }
}
