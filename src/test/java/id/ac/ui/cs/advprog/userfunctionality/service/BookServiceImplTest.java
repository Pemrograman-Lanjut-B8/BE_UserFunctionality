package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.dto.BookTopDTO;
import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetBookRecommendation() {
        // Mock data
        List<BookTopDTO> dummyBookTopDTOList = new ArrayList<>();
        // Add dummy BookTopDTO objects to the list

        // Mock repository method
        when(bookRepository.findTop5Book()).thenReturn(dummyBookTopDTOList);

        // Call the service method
        List<BookTopDTO> result = bookService.getBookRecommendation();

        // Assertions
        assertEquals(dummyBookTopDTOList.size(), result.size());
        // Add more assertions if needed
    }

    @Test
    void testFindByIsbn() {
        // Mock data
        String isbn = "123456789";
        Book dummyBook = new Book();
        // Set dummy Book properties

        // Mock repository method
        when(bookRepository.findByIsbn(isbn)).thenReturn(java.util.Optional.of(dummyBook));

        // Call the service method
        Book result = bookService.findByIsbn(isbn);

        // Assertions
        assertEquals(dummyBook, result);
        // Add more assertions if needed
    }
}
