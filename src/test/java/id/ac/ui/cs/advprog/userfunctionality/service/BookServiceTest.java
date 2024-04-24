package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookServiceTest {

    @Test
    public void testGetAllBooks() {
        BookService bookService = new BookService();
        List<Book> books = bookService.getAllBooks();
        assertEquals(3, books.size()); // assuming 3 dummy books are added
    }
}
