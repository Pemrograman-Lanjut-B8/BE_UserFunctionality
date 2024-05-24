package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
