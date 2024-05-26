package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.dto.BookTopDTO;
import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;
    private List<Book> bookList;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setIsbn("123");
        book.setJudulBuku("Book Title");
        book.setPenulis("Author");
        book.setPenerbit("Publisher");
        book.setDeskripsi("Description");
        book.setHarga(10000);
        book.setStok(10);
        book.setTanggalTerbit(java.time.LocalDate.now());
        book.setJumlahHalaman(100);
        book.setFotoCover("cover.jpg");
        book.setKategori("Category");

        bookList = new ArrayList<>();
        bookList.add(book);
    }

    @Test
    public void testGetBookRecommendation() {
        List<BookTopDTO> topBooks = new ArrayList<>();
        when(bookRepository.findTop5Book()).thenReturn(topBooks);

        List<BookTopDTO> result = bookService.getBookRecommendation();
        assertThat(result).isEqualTo(topBooks);
    }

    @Test
    public void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(bookList);

        List<Book> result = bookService.getAllBooks();
        assertThat(result).isEqualTo(bookList);
    }

    @Test
    public void testSearchBooks_WithTitleAndAuthor() {
        Page<Book> bookPage = new PageImpl<>(bookList);
        when(bookRepository.findByJudulBukuContainingIgnoreCaseAndPenulisContainingIgnoreCase(any(String.class), any(String.class), any(Pageable.class)))
                .thenReturn(bookPage);

        Page<Book> result = bookService.searchBooks("Book Title", "Author", "judulBuku", "ASC", 1);
        assertThat(result).isEqualTo(bookPage);
    }

    @Test
    public void testSearchBooks_WithTitleOnly() {
        Page<Book> bookPage = new PageImpl<>(bookList);
        when(bookRepository.findByJudulBukuContainingIgnoreCase(any(String.class), any(Pageable.class)))
                .thenReturn(bookPage);

        Page<Book> result = bookService.searchBooks("Book Title", null, "judulBuku", "ASC", 1);
        assertThat(result).isEqualTo(bookPage);
    }

    @Test
    public void testSearchBooks_WithAuthorOnly() {
        Page<Book> bookPage = new PageImpl<>(bookList);
        when(bookRepository.findByPenulisContainingIgnoreCase(any(String.class), any(Pageable.class)))
                .thenReturn(bookPage);

        Page<Book> result = bookService.searchBooks(null, "Author", "penulis", "ASC", 1);
        assertThat(result).isEqualTo(bookPage);
    }

    @Test
    public void testSearchBooks_NoTitleNoAuthor() {
        Page<Book> bookPage = new PageImpl<>(bookList);
        when(bookRepository.findAll(any(Pageable.class))).thenReturn(bookPage);

        Page<Book> result = bookService.searchBooks(null, null, "judulBuku", "ASC", 1);
        assertThat(result).isEqualTo(bookPage);
    }

    @Test
    public void testFindByIsbn() {
        when(bookRepository.findByIsbn("123")).thenReturn(book);

        Book result = bookService.findByIsbn("123");
        assertThat(result).isEqualTo(book);
    }
}
