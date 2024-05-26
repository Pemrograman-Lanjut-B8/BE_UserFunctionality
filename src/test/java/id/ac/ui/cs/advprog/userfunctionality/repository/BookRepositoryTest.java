//package id.ac.ui.cs.advprog.userfunctionality.repository;
//
//import id.ac.ui.cs.advprog.userfunctionality.dto.BookTopDTO;
//import id.ac.ui.cs.advprog.userfunctionality.model.Book;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@SpringBootTest
//public class BookRepositoryTest {
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    @BeforeEach
//    public void setUp() {
//        Book book1 = new Book();
//        book1.setIsbn("123");
//        book1.setJudulBuku("Book Title 1");
//        book1.setPenulis("Author 1");
//        book1.setPenerbit("Publisher 1");
//        book1.setDeskripsi("Description 1");
//        book1.setHarga(10000);
//        book1.setStok(10);
//        book1.setTanggalTerbit(LocalDate.now());
//        book1.setJumlahHalaman(100);
//        book1.setFotoCover("cover1.jpg");
//        book1.setKategori("Category 1");
//
//        Book book2 = new Book();
//        book2.setIsbn("456");
//        book2.setJudulBuku("Book Title 2");
//        book2.setPenulis("Author 2");
//        book2.setPenerbit("Publisher 2");
//        book2.setDeskripsi("Description 2");
//        book2.setHarga(20000);
//        book2.setStok(20);
//        book2.setTanggalTerbit(LocalDate.now());
//        book2.setJumlahHalaman(200);
//        book2.setFotoCover("cover2.jpg");
//        book2.setKategori("Category 2");
//
//        Book book3 = new Book();
//        book3.setIsbn("789");
//        book3.setJudulBuku("Another Book Title");
//        book3.setPenulis("Author 3");
//        book3.setPenerbit("Publisher 3");
//        book3.setDeskripsi("Description 3");
//        book3.setHarga(15000);
//        book3.setStok(30);
//        book3.setTanggalTerbit(LocalDate.now());
//        book3.setJumlahHalaman(150);
//        book3.setFotoCover("cover3.jpg");
//        book3.setKategori("Category 3");
//
//        bookRepository.save(book1);
//        bookRepository.save(book2);
//        bookRepository.save(book3);
//    }
//
//    @Test
//    public void testFindTop5Book() {
//        List<BookTopDTO> topBooks = bookRepository.findTop5Book();
//        assertThat(topBooks).isNotNull();
//        assertThat(topBooks.size()).isLessThanOrEqualTo(5);
//    }
//
//    @Test
//    public void testFindByJudulBukuContainingIgnoreCaseAndPenulisContainingIgnoreCase() {
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<Book> books = bookRepository.findByJudulBukuContainingIgnoreCaseAndPenulisContainingIgnoreCase("Book", "Author", pageable);
//        assertThat(books).isNotNull();
//        assertThat(books.getTotalElements()).isEqualTo(2);
//    }
//
//    @Test
//    public void testFindByJudulBukuContainingIgnoreCase() {
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<Book> books = bookRepository.findByJudulBukuContainingIgnoreCase("Book", pageable);
//        assertThat(books).isNotNull();
//        assertThat(books.getTotalElements()).isEqualTo(2);
//    }
//
//    @Test
//    public void testFindByPenulisContainingIgnoreCase() {
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<Book> books = bookRepository.findByPenulisContainingIgnoreCase("Author", pageable);
//        assertThat(books).isNotNull();
//        assertThat(books.getTotalElements()).isEqualTo(3);
//    }
//
//    @Test
//    public void testFindByIsbn() {
//        Book book = bookRepository.findByIsbn("123");
//        assertThat(book).isNotNull();
//        assertThat(book.getJudulBuku()).isEqualTo("Book Title 1");
//    }
//}
