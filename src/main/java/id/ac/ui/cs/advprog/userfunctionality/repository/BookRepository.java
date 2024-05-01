package id.ac.ui.cs.advprog.userfunctionality.repository;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    // You can replace this with actual database operations
    public List<Book> getAllBooks() {
        // Dummy data for now
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "Book A", "Author A", "Description for Book A", 4.5));
        books.add(new Book(2L, "Book B", "Author B", "Description for Book B", 4.6));
        books.add(new Book(3L, "Book C", "Author C", "Description for Book C", 4.8));
        return books;
    }
}
