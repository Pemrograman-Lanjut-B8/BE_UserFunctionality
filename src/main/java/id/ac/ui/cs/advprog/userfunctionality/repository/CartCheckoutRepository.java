package id.ac.ui.cs.advprog.userfunctionality.repository;

import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartCheckoutRepository extends JpaRepository<CartCheckout, Long> {

}
