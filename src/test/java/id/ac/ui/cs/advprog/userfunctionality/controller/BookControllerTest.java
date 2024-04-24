package id.ac.ui.cs.advprog.userfunctionality.controller;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookControllerTest {

    private BookController bookController;
    private BookService bookService;
    private Model model;

    @BeforeEach
    public void setUp() {
        bookService = mock(BookService.class);
        model = mock(Model.class);
        bookController = new BookController(bookService);
    }

    @Test
    public void testShowLandingPage() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "Book A", "Author A", "Description A"));
        books.add(new Book(2L, "Book B", "Author B", "Description B"));
        when(bookService.getAllBooks()).thenReturn(books);

        ModelAndView modelAndView = bookController.showLandingPage(model);
        String viewName = modelAndView.getViewName();
        assertEquals("LandingPage", viewName);

        // Verify that the model contains the correct attribute
        assertEquals(books, modelAndView.getModel().get("books"));
    }
}
