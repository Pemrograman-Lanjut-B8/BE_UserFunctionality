package id.ac.ui.cs.advprog.userfunctionality.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {

    @Test
    public void testBookConstructor() {
        Book book = new Book(1L, "Title", "Author", "Description");
        assertEquals(1L, book.getId());
        assertEquals("Title", book.getTitle());
        assertEquals("Author", book.getAuthor());
        assertEquals("Description", book.getDescription());
    }
}
