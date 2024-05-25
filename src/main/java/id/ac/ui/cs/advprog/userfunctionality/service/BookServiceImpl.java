package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

//    @Override
//    public List<Book> getAllBooks() {
//
//        return bookRepository.getAllBooks();
//    }
//
//    public List<Book> getTopRatedBooks() {
//        List<Book> allBooks = bookRepository.getAllBooks();
//
//        if (allBooks.isEmpty()) {
//            return Collections.emptyList();
//        }
//
//        return allBooks.stream()
//                .sorted(Comparator.comparing(Book::getRating).reversed())
//                .collect(Collectors.toList());
//    }

    @Override
    public List<Book> getBookRecommendation() {

        return bookRepository.findTop5ByOrderByRatingDesc();
    }

    @Override
    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElse(null);
    }
}
