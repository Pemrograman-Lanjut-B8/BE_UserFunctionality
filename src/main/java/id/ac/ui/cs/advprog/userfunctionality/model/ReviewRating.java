package id.ac.ui.cs.advprog.userfunctionality.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

import java.util.UUID;
import java.time.LocalDateTime;

@Getter @Setter
public class ReviewRating {
    private String reviewId;
    private String username;
    private String bookId;
    private String review;
    private int rating;
    private LocalDateTime dateTime;

    public ReviewRating(){
        this.reviewId = UUID.randomUUID().toString();
        this.dateTime = LocalDateTime.now();
    }

    public void setReview(String review) {
        if (!review.isEmpty()) {
            this.review = review;
        } else {
            throw new IllegalArgumentException("Review harus Anda isi!");
        }
    }

    public void setRating(int rating) {
        if (rating <= 0) {
            this.rating = 0;
        } else if (rating >= 10) {
            this.rating = 10;
        } else {
            this.rating = rating;
        }
    }
}