package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    List<Book> getBookRecommendation();
    public List<Book> getAllBooks();
    public Page<Book> searchBooks(String judulBuku, String penulis, String sortBy, String sortDirection, int pageIndex);
    Book findByIsbn(String isbn);
}
