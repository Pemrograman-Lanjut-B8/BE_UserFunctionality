package id.ac.ui.cs.advprog.userfunctionality.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BookRepositoryTest {

    @InjectMocks
    private BookRepository bookRepository;

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

        // Call the method under test
        List<Book> actualBooks = bookRepository.getAllBooks();

        // Verify the result
        assertEquals(expectedBooks.size(), actualBooks.size());
        for (int i = 0; i < expectedBooks.size(); i++) {
            assertEquals(expectedBooks.get(i).getId(), actualBooks.get(i).getId());
            assertEquals(expectedBooks.get(i).getTitle(), actualBooks.get(i).getTitle());
            assertEquals(expectedBooks.get(i).getAuthor(), actualBooks.get(i).getAuthor());
            assertEquals(expectedBooks.get(i).getDescription(), actualBooks.get(i).getDescription());
        }
    }
}
