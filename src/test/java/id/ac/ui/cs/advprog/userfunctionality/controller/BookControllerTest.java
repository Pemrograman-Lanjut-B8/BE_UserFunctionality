package id.ac.ui.cs.advprog.userfunctionality.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

import id.ac.ui.cs.advprog.userfunctionality.controller.BookController;
import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private Model model;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowLandingPage() {
        // Stub BookService
        Book book1 = new Book(1L, "Book A", "Author A", "Description for Book A");
        Book book2 = new Book(2L, "Book B", "Author B", "Description for Book B");
        Book book3 = new Book(3L, "Book C", "Author C", "Description for Book C");
        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        // Exercise
        String viewName = bookController.showLandingPage(model);

        // Verify
        verify(model).addAttribute("books", Arrays.asList(book1, book2));
        assertEquals("LandingPage", viewName);
    }
}
