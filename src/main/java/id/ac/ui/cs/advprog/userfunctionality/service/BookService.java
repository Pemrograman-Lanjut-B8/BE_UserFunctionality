package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import java.util.List;

public interface BookService {
//    List<Book> getAllBooks();
//    List<Book> getTopRatedBooks();
    List<Book> getBookRecommendation();
    Book findByIsbn(String isbn);


}
