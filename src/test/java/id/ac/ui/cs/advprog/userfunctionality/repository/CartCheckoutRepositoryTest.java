package id.ac.ui.cs.advprog.userfunctionality.repository;

import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CartCheckoutRepositoryTest {
    private CartCheckoutRepository repository;

    @BeforeEach
    void setUp() {
        repository = new CartCheckoutRepository();
    }

    @Test
    void testCreateAndFindById() {
        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setCartId(1L);
        cartCheckout.setUserId("user123");
        cartCheckout.setTotalPrice(100.0);
        CartCheckout created = repository.create(cartCheckout);

        Optional<CartCheckout> found = repository.findById(1L);
        assertTrue(found.isPresent(), "Cart should be found");
        assertSame(created, found.get(), "The found cart should be the same as the created one");
    }

    @Test
    void testFindAllWhenEmpty() {
        assertTrue(repository.findAll().isEmpty(), "FindAll should return an empty list if no carts are stored");
    }

    @Test
    void testDeleteNonexistentCartCheckout() {
        assertFalse(repository.delete(999L), "Deleting a non-existent cart should return false");
    }

    @Test
    void testUpdateNonexistentCartCheckout() {
        CartCheckout updatedCartCheckout = new CartCheckout();
        updatedCartCheckout.setCartId(999L); // Non-existent ID
        assertThrows(IllegalArgumentException.class, () -> repository.update(999L, updatedCartCheckout),
                "Should throw an exception when trying to update a non-existent cart");
    }

    @Test
    void testDeleteCartCheckout() {
        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setCartId(1L);
        cartCheckout.setUserId("user123");
        cartCheckout.setTotalPrice(100.0);
        repository.create(cartCheckout);

        boolean deleted = repository.delete(1L);
        assertTrue(deleted, "Deleting an existing cart should return true");
        assertTrue(repository.findAll().isEmpty(), "All carts should be deleted");
    }
}
