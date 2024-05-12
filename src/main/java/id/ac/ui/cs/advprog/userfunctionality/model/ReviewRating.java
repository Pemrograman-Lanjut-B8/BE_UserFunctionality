package id.ac.ui.cs.advprog.userfunctionality.model;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "review_rating")
public class ReviewRating {

    @Id
    @Column(name = "review_id")
    private String reviewId;

    @Column(name = "username")
    private String username;

    @Column(name = "book_id")
    private String bookId;

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

//    public void setReview(String review) {
//        if (!review.isEmpty()) {
//            this.review = review;
//        } else {
//            throw new IllegalArgumentException("Review harus Anda isi!");
//        }
//    }
//
//    public void setRating(int rating) {
//        if (rating < 0) {
//            this.rating = 0;
//        } else if (rating > 10) {
//            this.rating = 10;
//        } else {
//            this.rating = rating;
//        }
//    }
}
