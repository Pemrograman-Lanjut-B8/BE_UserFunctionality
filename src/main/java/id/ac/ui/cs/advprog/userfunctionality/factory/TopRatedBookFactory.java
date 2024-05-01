package id.ac.ui.cs.advprog.userfunctionality.factory;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.service.BookService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TopRatedBookFactory {

    private final BookService bookService;

    public TopRatedBookFactory(BookService bookService) {
        this.bookService = bookService;
    }

    public List<Book> getTopRatedBooks() {
        List<Book> allBooks = bookService.getAllBooks();

        if (allBooks.isEmpty()) {
            return Collections.emptyList();
        }

        return allBooks.stream()
                .sorted(Comparator.comparing(Book::getRating).reversed())
                .collect(Collectors.toList());
    }

}
