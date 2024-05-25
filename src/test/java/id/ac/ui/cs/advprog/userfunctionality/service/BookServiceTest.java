package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.dto.BookTopDTO;
import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void testGetBookRecommendation() {
        // Mock data
        Book book1 = new Book();
        book1.setJudulBuku("Book 1");
        book1.setFotoCover("cover1.jpg");
        book1.setPenulis("Author 1");
        book1.setIsbn("1234567890");
        book1.setKategori("Fiksi");

        Book book2 = new Book();
        book2.setJudulBuku("Book 2");
        book2.setFotoCover("cover2.jpg");
        book2.setPenulis("Author 2");
        book2.setIsbn("0987654321");
        book2.setKategori("Ilmiah");

        when(bookRepository.findTop5Book()).thenReturn(Arrays.<BookTopDTO>asList((BookTopDTO) book1, (BookTopDTO) book2));

        // Test
        List<BookTopDTO> recommendation = bookService.getBookRecommendation();

        // Assertions
        assertEquals(2, recommendation.size());
        assertEquals("Book 1", recommendation.get(0).getJudulBuku());
        assertEquals("cover1.jpg", recommendation.get(0).getFotoCover());
        assertEquals("Author 1", recommendation.get(0).getPenulis());
        assertEquals("1234567890", recommendation.get(0).getIsbn());
        assertEquals(4.5, recommendation.get(0).getAverageRating());

        assertEquals("Book 2", recommendation.get(1).getJudulBuku());
        assertEquals("cover2.jpg", recommendation.get(1).getFotoCover());
        assertEquals("Author 2", recommendation.get(1).getPenulis());
        assertEquals("0987654321", recommendation.get(1).getIsbn());
        assertEquals(4.0, recommendation.get(1).getAverageRating());
    }
}
