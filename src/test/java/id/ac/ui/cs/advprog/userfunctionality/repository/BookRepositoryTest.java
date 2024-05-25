package id.ac.ui.cs.advprog.userfunctionality.repository;

import id.ac.ui.cs.advprog.userfunctionality.dto.BookTopDTO;
import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        // Setting up initial data for the test
        Book book1 = new Book();
        book1.setJudulBuku("Book One");
        book1.setPenulis("Author One");
        book1.setPenerbit("Publisher One");
        book1.setDeskripsi("Description One");
        book1.setHarga(10.0);
        book1.setStok(5);
        book1.setTanggalTerbit(LocalDate.now());
        book1.setIsbn("123");
        book1.setJumlahHalaman(100);
        book1.setFotoCover("cover1.jpg");
        book1.setKategori("Fiction");

        Book book2 = new Book();
        book2.setJudulBuku("Book Two");
        book2.setPenulis("Author Two");
        book2.setPenerbit("Publisher Two");
        book2.setDeskripsi("Description Two");
        book2.setHarga(15.0);
        book2.setStok(3);
        book2.setTanggalTerbit(LocalDate.now());
        book2.setIsbn("456");
        book2.setJumlahHalaman(200);
        book2.setFotoCover("cover2.jpg");
        book2.setKategori("Science");

        Book book3 = new Book();
        book3.setJudulBuku("Book Three");
        book3.setPenulis("Author Three");
        book3.setPenerbit("Publisher Three");
        book3.setDeskripsi("Description Three");
        book3.setHarga(20.0);
        book3.setStok(7);
        book3.setTanggalTerbit(LocalDate.now());
        book3.setIsbn("789");
        book3.setJumlahHalaman(300);
        book3.setFotoCover("cover3.jpg");
        book3.setKategori("History");

        Book book4 = new Book();
        book4.setJudulBuku("Book Four");
        book4.setPenulis("Author Four");
        book4.setPenerbit("Publisher Four");
        book4.setDeskripsi("Description Four");
        book4.setHarga(25.0);
        book4.setStok(1);
        book4.setTanggalTerbit(LocalDate.now());
        book4.setIsbn("101");
        book4.setJumlahHalaman(400);
        book4.setFotoCover("cover4.jpg");
        book4.setKategori("Technology");

        Book book5 = new Book();
        book5.setJudulBuku("Book Five");
        book5.setPenulis("Author Five");
        book5.setPenerbit("Publisher Five");
        book5.setDeskripsi("Description Five");
        book5.setHarga(30.0);
        book5.setStok(2);
        book5.setTanggalTerbit(LocalDate.now());
        book5.setIsbn("102");
        book5.setJumlahHalaman(500);
        book5.setFotoCover("cover5.jpg");
        book5.setKategori("Math");

        Book book6 = new Book();
        book6.setJudulBuku("Book Six");
        book6.setPenulis("Author Six");
        book6.setPenerbit("Publisher Six");
        book6.setDeskripsi("Description Six");
        book6.setHarga(35.0);
        book6.setStok(6);
        book6.setTanggalTerbit(LocalDate.now());
        book6.setIsbn("103");
        book6.setJumlahHalaman(600);
        book6.setFotoCover("cover6.jpg");
        book6.setKategori("Art");

        entityManager.persist(book1);
        entityManager.persist(book2);
        entityManager.persist(book3);
        entityManager.persist(book4);
        entityManager.persist(book5);
        entityManager.persist(book6);

    }

    @Test
    public void whenFindTop5Book_thenReturnTop5BooksOrderedByAverageRating() {
        List<BookTopDTO> topBooks = bookRepository.findTop5Book();

        assertThat(topBooks).hasSize(5);
        assertThat(topBooks.get(0).getIsbn()).isEqualTo("103"); // Book with highest average rating
        assertThat(topBooks.get(1).getIsbn()).isEqualTo("123"); // Second highest average rating
        assertThat(topBooks.get(2).getIsbn()).isEqualTo("456");
        assertThat(topBooks.get(3).getIsbn()).isEqualTo("789");
        assertThat(topBooks.get(4).getIsbn()).isEqualTo("101"); // Fifth highest average rating
    }
}
