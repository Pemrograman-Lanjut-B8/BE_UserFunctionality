//package id.ac.ui.cs.advprog.userfunctionality.factory;
//
//import id.ac.ui.cs.advprog.userfunctionality.model.Book;
//import id.ac.ui.cs.advprog.userfunctionality.service.BookService;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//public class TopRatedBookFactoryTest {
//
//    @Test
//    public void testGetTopRatedBooks() {
//
//        BookService bookService = new MockBookService();
//        TopRatedBookFactory topRatedBookFactory = new TopRatedBookFactory(bookService);
//
//        // Tindakan
//        List<Book> topRatedBooks = topRatedBookFactory.getTopRatedBooks();
//
//        // Verifikasi
//        assertNotNull(topRatedBooks);
//        assertFalse(topRatedBooks.isEmpty());
//
//    }
//
//    // Kelas mock sederhana untuk BookService
//    private class MockBookService implements BookService {
//        @Override
//        public List<Book> getAllBooks() {
//
//            return prepareMockBooks();
//        }
//
//        @Override
//        public List<Book> getTopRatedBooks() {
//            return prepareMockBooks();
//        }
//
//        private List<Book> prepareMockBooks() {
//
//            List<Book> mockBooks = new ArrayList<>();
//            mockBooks.add(new Book(1L, "Book A", "Author A", "Description for Book A", 4.5));
//            mockBooks.add(new Book(2L, "Book B", "Author B", "Description for Book B", 4.6));
//            mockBooks.add(new Book(3L, "Book C", "Author C", "Description for Book C", 4.8));
//
//            return mockBooks;
//        }
//    }
//}
