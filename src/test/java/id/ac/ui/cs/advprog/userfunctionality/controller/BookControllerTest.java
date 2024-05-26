package id.ac.ui.cs.advprog.userfunctionality.controller;

import id.ac.ui.cs.advprog.userfunctionality.dto.BookTopDTO;
import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RestController
@CrossOrigin
class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserFrontPage() {
        String response = bookController.userFrontPage();
        assertEquals("<h1>Welcome to User Page</h1>", response);
    }

    @Test
    void testGetRecommendation() {
        List<BookTopDTO> recommendedBooks = Collections.singletonList(null);
        when(bookService.getBookRecommendation()).thenReturn(recommendedBooks);

        ResponseEntity<?> response = bookController.getRecommendation();
        assertEquals(ResponseEntity.ok(recommendedBooks), response);
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = Collections.singletonList(new Book());
        when(bookService.getAllBooks()).thenReturn(books);

        List<Book> response = bookController.getAllBooks();
        assertEquals(books, response);
    }

    @Test
    void testGetBooks() {
        Page<Book> bookPage = new PageImpl<>(Collections.singletonList(new Book()));
        when(bookService.searchBooks("judul", "penulis", "tanggalTerbit", "DESC", 1)).thenReturn(bookPage);

        Page<Book> response = bookController.getBooks("judul", "penulis", "tanggalTerbit", "DESC", 1);
        assertEquals(bookPage, response);
    }

    @Test
    void testGetBookById() {
        Book book = new Book();
        when(bookService.findByIsbn("123")).thenReturn(book);

        Book response = bookController.getBookById("123");
        assertEquals(book, response);
    }
}
