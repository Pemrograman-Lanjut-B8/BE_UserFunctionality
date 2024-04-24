package id.ac.ui.cs.advprog.userfunctionality.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService = new BookServiceImpl();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        // Prepare dummy data
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book(1L, "Book A", "Author A", "Description for Book A"));
        expectedBooks.add(new Book(2L, "Book B", "Author B", "Description for Book B"));
        expectedBooks.add(new Book(3L, "Book C", "Author C", "Description for Book C"));

        // Mock the behavior of the BookRepository
        when(bookRepository.getAllBooks()).thenReturn(expectedBooks);

        // Call the method under test
        List<Book> actualBooks = bookService.getAllBooks();

        // Verify the result
        assertEquals(expectedBooks.size(), actualBooks.size());
        for (int i = 0; i < expectedBooks.size(); i++) {
            assertEquals(expectedBooks.get(i), actualBooks.get(i));
        }
    }
}
