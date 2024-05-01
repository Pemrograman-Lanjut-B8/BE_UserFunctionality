package id.ac.ui.cs.advprog.userfunctionality.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {

    private Long id;
    private String title;
    private String author;
    private String description;
    private double rating;
    
    public Book(Long id, String title, String author, String description, Double rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.rating = rating;
    }

}


