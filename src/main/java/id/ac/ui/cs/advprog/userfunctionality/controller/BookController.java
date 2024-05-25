package id.ac.ui.cs.advprog.userfunctionality.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.model.builders.BookBuilderImpl;
import id.ac.ui.cs.advprog.userfunctionality.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookBuilderImpl bookBuilder;

    @GetMapping("/")
    @ResponseBody
    public String userFrontPage() {
        return "<h1>Welcome to User Page</h1>";
    }

    @GetMapping("/recommendation")
    public ResponseEntity<?> getRecommendation() {
        List<Book> recommendedBooks = bookService.getBookRecommendation();
        return ResponseEntity.ok(recommendedBooks);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/search")
    public Page<Book> getBooks(
            @RequestParam(required = false) String judulBuku,
            @RequestParam(required = false) String penulis,
            @RequestParam(defaultValue = "tanggalTerbit") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection,
            @RequestParam(defaultValue = "1") int pageIndex) {
        return bookService.searchBooks(judulBuku, penulis, sortBy, sortDirection, pageIndex);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{isbn}")
    public Book getBookById(@PathVariable String isbn) {
        return bookService.findByIsbn(isbn);
    }
}