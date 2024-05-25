package id.ac.ui.cs.advprog.userfunctionality.controller;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.service.ReviewRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/review")
public class ReviewRatingController {

    @Autowired
    private ReviewRatingService reviewRatingService;

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<Object>> createReviewRating(@RequestBody ReviewRating reviewRating) {
        return reviewRatingService.createReviewRating(reviewRating)
                .thenApply(createdReviewRating -> ResponseEntity.status(HttpStatus.CREATED).body((Object) createdReviewRating))
                .exceptionally(ex -> {
                    System.out.println("Error in create review rating: " + ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating review rating");
                });
    }

    @GetMapping("/list")
    public CompletableFuture<ResponseEntity<Object>> reviewRatingList() {
        return reviewRatingService.findAll()
                .thenApply(allReviewRatings -> ResponseEntity.ok((Object) allReviewRatings))
                .exceptionally(ex -> {
                    System.out.println("Error in list review ratings: " + ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error listing review ratings");
                });
    }

    @PutMapping("/edit/{reviewId}")
    public CompletableFuture<ResponseEntity<Object>> editReviewRating(@PathVariable("reviewId") String reviewId, @RequestBody ReviewRating reviewRating) {
        return reviewRatingService.updateReviewRating(reviewId, reviewRating)
                .thenApply(updatedReviewRating -> ResponseEntity.ok((Object) updatedReviewRating))
                .exceptionally(ex -> {
                    System.out.println("Error in edit review rating: " + ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error editing review rating");
                });
    }

    @DeleteMapping("/delete/{reviewId}")
    public CompletableFuture<ResponseEntity<Object>> deleteReviewRating(@PathVariable("reviewId") String reviewId) {
        return reviewRatingService.deleteReviewRating(reviewId)
                .thenApply(voidResult -> ResponseEntity.noContent().build())
                .exceptionally(ex -> {
                    System.out.println("Error in delete review rating: " + ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }
}
