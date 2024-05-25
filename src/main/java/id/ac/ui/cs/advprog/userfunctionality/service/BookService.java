package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.dto.BookTopDTO;
import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import java.util.List;

public interface BookService {
    List<BookTopDTO> getBookRecommendation();
    Book findByIsbn(String isbn);


}
