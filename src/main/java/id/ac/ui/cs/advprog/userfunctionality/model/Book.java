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

    public Book(Long id, String title, String author, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
    }

}


