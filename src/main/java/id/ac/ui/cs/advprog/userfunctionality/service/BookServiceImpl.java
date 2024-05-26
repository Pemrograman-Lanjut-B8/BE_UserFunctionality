package id.ac.ui.cs.advprog.userfunctionality.service;

import com.github.javafaker.Faker;
import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBookRecommendation() {
        return bookRepository.findTop5ByOrderByRatingDesc();
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Page<Book> searchBooks(String judulBuku, String penulis, String sortBy, String sortDirection, int pageIndex) {
        Pageable pageable = PageRequest.of(pageIndex - 1, 25, Sort.Direction.fromString(sortDirection), sortBy);
        if (judulBuku != null && penulis != null) {
            return bookRepository.findByJudulBukuContainingIgnoreCaseAndPenulisContainingIgnoreCase(judulBuku, penulis, pageable);
        } else if (judulBuku != null) {
            return bookRepository.findByJudulBukuContainingIgnoreCase(judulBuku, pageable);
        } else if (penulis != null) {
            return bookRepository.findByPenulisContainingIgnoreCase(penulis, pageable);
        } else {
            return bookRepository.findAll(pageable);
        }
    }

    public Book getBookById(String isbn) {
        return bookRepository.findById(isbn).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    private String truncate(String value, int length) {
        return value.length() > length ? value.substring(0, length) : value;
    }

    @Override
    public void seed() {
        Faker faker = new Faker();
        Random random = new Random();

        IntStream.range(0, 1000).forEach(i -> {
            Book book = new Book();
            book.setJudulBuku(truncate(faker.book().title(), 255));
            book.setPenulis(truncate(faker.book().author(), 255));
            book.setPenerbit(truncate(faker.company().name(), 255));
            book.setDeskripsi(truncate(faker.lorem().paragraph(), 255));
            book.setHarga(faker.number().randomDouble(2, 50, 200));
            book.setStok(faker.number().numberBetween(0, 100));
            book.setTanggalTerbit(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            book.setIsbn(truncate(faker.code().isbn13(), 255));
            book.setJumlahHalaman(faker.number().numberBetween(100, 1000));
            book.setFotoCover("https://pbs.twimg.com/media/GOaCxl9aEAAG2Zg?format=jpg&name=medium");
            book.setKategori(truncate(faker.book().genre(), 255));
            book.setRating(faker.number().randomDouble(1, 1, 5));

            bookRepository.save(book);
        });
    }

}
