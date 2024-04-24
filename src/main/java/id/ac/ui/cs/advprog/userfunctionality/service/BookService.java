package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;


@Service
public class BookService {

    public List<Book> getAllBooks() {
        // Dummy data
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "Book A", "Author A", "Description for Book A"));
        books.add(new Book(2L, "Book B", "Author B", "Description for Book B"));
        books.add(new Book(3L, "Book C", "Author C", "Description for Book C"));
        return books;
    }
}
