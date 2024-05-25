package id.ac.ui.cs.advprog.userfunctionality.model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "review_rating")
public class ReviewRating {

    @Setter
    @Getter
    @Id
    @Column(name = "review_id")
    private String reviewId;

    @Column(name = "username")
    private String username;

    @ManyToOne
    private Book book;

    @Column(name = "review")
    private String review;

    @Column(name = "rating")
    private int rating;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public ReviewRating() {
        this.reviewId = UUID.randomUUID().toString();
        this.dateTime = LocalDateTime.now();
    }

    public ReviewRating(String username, String review, int rating) {
        this();
        this.username = username;
        setReview(review);
        setRating(rating);
    }

    public void setReview(String review) {
        if (!review.isEmpty()) {
            this.review = review;
        } else {
            throw new IllegalArgumentException("Review harus Anda isi!");
        }
    }

    public void setRating(int rating) {
        if (rating < 0) {
            this.rating = 0;
        } else if (rating > 10) {
            this.rating = 10;
        } else {
            this.rating = rating;
        }
    }
}