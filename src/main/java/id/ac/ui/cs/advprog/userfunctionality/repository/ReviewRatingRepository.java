package id.ac.ui.cs.advprog.userfunctionality.repository;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ReviewRatingRepository extends JpaRepository <ReviewRating, String> {
}