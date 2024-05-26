package id.ac.ui.cs.advprog.userfunctionality.repository;

import id.ac.ui.cs.advprog.userfunctionality.dto.BookTopDTO;
import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    @Query(value = "SELECT book.judul_buku AS judulBuku, book.foto_cover AS fotoCover, book.penulis AS penulis, book.isbn AS isbn, \n" +
            "COALESCE(AVG(review_rating.rating),0) AS averageRating\n" +
            "FROM book LEFT OUTER JOIN review_rating \n" +
            "ON book.isbn=review_rating.book_isbn\n" +
            "GROUP BY book.judul_buku, book.foto_cover, book.penulis, book.isbn\n" +
            "ORDER BY averageRating DESC \n" +
            "LIMIT 5", nativeQuery = true)
    List<BookTopDTO> findTop5Book();

    Page<Book> findByJudulBukuContainingIgnoreCaseAndPenulisContainingIgnoreCase(String judulBuku, String penulis, Pageable pageable);
    Page<Book> findByJudulBukuContainingIgnoreCase(String judulBuku, Pageable pageable);
    Page<Book> findByPenulisContainingIgnoreCase(String penulis, Pageable pageable);
    Book findByIsbn(String isbn);

}
