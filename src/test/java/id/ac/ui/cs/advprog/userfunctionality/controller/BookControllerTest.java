package id.ac.ui.cs.advprog.userfunctionality.controller;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

    private List<Book> sampleBooks;

    @BeforeEach
    void setUp() {
        // Initialize sample books
        sampleBooks = Arrays.asList(
                new Book(1L, "Book A", "Author A", "Description for Book A", 4.5),
                new Book(2L, "Book B", "Author B", "Description for Book B", 4.6),
                new Book(3L, "Book C", "Author C", "Description for Book C", 4.8)
        );

        // Mock BookService to return sample books
        bookService = new BookService() {
            @Override
            public List<Book> getTopRatedBooks() {
                return sampleBooks.subList(0, 2); // Return first 2 books
            }

            @Override
            public List<Book> getAllBooks() {
                return sampleBooks;
            }
        };
    }

    @Test
    public void testGetTopRatedBooks() throws Exception {
        // Perform GET request to /api/books/top-rated
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/top-rated"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

    @Test
    public void testGetAllBooks() throws Exception {
        // Perform GET request to /api/books/all
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(sampleBooks.size())); // Expecting all sample books
    }
}
