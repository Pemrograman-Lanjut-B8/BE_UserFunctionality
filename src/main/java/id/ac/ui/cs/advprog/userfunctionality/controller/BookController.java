package id.ac.ui.cs.advprog.userfunctionality.controller;

import java.util.List;

import id.ac.ui.cs.advprog.userfunctionality.dto.BookTopDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
        List<BookTopDTO> recommendedBooks = bookService.getBookRecommendation();
        return ResponseEntity.ok(recommendedBooks);
    }

}