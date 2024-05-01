package id.ac.ui.cs.advprog.userfunctionality.controller;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/top-rated")
    public List<Book> getTopRatedBooks() {
        return bookService.getTopRatedBooks();
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}
