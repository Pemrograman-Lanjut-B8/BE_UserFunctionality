package id.ac.ui.cs.advprog.userfunctionality.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

import java.util.UUID;

@Getter @Setter
public class ReviewRating {
    public ReviewRating(){
        this.reviewId = UUID.randomUUID().toString();
    }
    private String reviewId;
    private String username;
    private String bookId;
    private String review;
    private int rating;
}
