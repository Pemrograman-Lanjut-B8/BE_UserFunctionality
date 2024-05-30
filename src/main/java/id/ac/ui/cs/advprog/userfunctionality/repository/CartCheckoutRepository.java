
package id.ac.ui.cs.advprog.userfunctionality.repository;

import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartCheckoutRepository extends JpaRepository<CartCheckout, Long> {
    Optional<CartCheckout> findByUserId(UUID userId);
}
