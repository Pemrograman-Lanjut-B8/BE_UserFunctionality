package id.ac.ui.cs.advprog.userfunctionality.repository;

import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findTop5ByOrderByRatingDesc();
    Page<Book> findByJudulBukuContainingIgnoreCaseAndPenulisContainingIgnoreCase(String judulBuku, String penulis, Pageable pageable);
    Page<Book> findByJudulBukuContainingIgnoreCase(String judulBuku, Pageable pageable);
    Page<Book> findByPenulisContainingIgnoreCase(String penulis, Pageable pageable);
}
