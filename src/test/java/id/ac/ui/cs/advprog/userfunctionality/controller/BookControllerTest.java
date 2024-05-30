package id.ac.ui.cs.advprog.userfunctionality.controller;

import id.ac.ui.cs.advprog.userfunctionality.dto.BookTopDTO;
import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void testUserFrontPage() throws Exception {
        mockMvc.perform(get("/api/book/"))
                .andExpect(status().isOk())
                .andExpect(content().string("<h1>Welcome to User Page</h1>"));
    }

    @Test
    void testGetRecommendation() {
        BookTopDTO bookTopDTO1 = mock(BookTopDTO.class);
        BookTopDTO bookTopDTO2 = mock(BookTopDTO.class);
        List<BookTopDTO> recommendedBooks = Arrays.asList(bookTopDTO1, bookTopDTO2);
        when(bookService.getBookRecommendation()).thenReturn(recommendedBooks);

        ResponseEntity<?> response = bookController.getRecommendation();

        assertEquals(ResponseEntity.ok(recommendedBooks), response);
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = Arrays.asList(new Book(), new Book());
        when(bookService.getAllBooks()).thenReturn(books);

        List<Book> result = bookController.getAllBooks();

        assertEquals(books, result);
    }

    @Test
    void testGetBooks() {
        Page<Book> books = new PageImpl<>(Arrays.asList(new Book(), new Book()));
        when(bookService.searchBooks(anyString(), anyString(), anyString(), anyString(), anyInt())).thenReturn(books);

        Page<Book> result = bookController.getBooks("judulBuku", "penulis", "tanggalTerbit", "DESC", 1);

        assertEquals(books, result);
    }

    @Test
    void testGetBookById() {
        Book book = new Book();
        when(bookService.findByIsbn("12345")).thenReturn(book);

        Book result = bookController.getBookById("12345");

        assertEquals(book, result);
    }
}
