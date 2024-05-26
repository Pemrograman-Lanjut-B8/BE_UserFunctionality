package id.ac.ui.cs.advprog.userfunctionality.controller;

import java.util.List;

import id.ac.ui.cs.advprog.userfunctionality.dto.BookTopDTO;
import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.model.builders.BookBuilderImpl;
import id.ac.ui.cs.advprog.userfunctionality.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    @ResponseBody
    public String userFrontPage() {
        return "<h1>Welcome to User Page</h1>";
    }

    @GetMapping("/recommendation")
    public ResponseEntity<?> getRecommendation() {
        List<BookTopDTO> recommendedBooks = bookService.getBookRecommendation();
        return ResponseEntity.ok(recommendedBooks);
    }

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

    @GetMapping("/{isbn}")
    public Book getBookById(@PathVariable String isbn) {
        return bookService.findByIsbn(isbn);
    }
}