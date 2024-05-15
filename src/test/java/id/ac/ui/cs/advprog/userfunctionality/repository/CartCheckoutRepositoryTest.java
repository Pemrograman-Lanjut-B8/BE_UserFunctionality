package id.ac.ui.cs.advprog.userfunctionality.repository;

import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import id.ac.ui.cs.advprog.userfunctionality.model.CartItems;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CartCheckoutRepositoryTest {

    private CartCheckoutRepository cartCheckoutRepository;

    @BeforeEach
    void setUp() {
        cartCheckoutRepository = new CartCheckoutRepository();
    }

    @Test
    void testCreateAndFind() {
        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setCartId(1L);
        cartCheckout.setUserId("user123");
        cartCheckout.setTotalPrice(100.0);
        cartCheckoutRepository.create(cartCheckout);

        Optional<CartCheckout> savedCartCheckout = cartCheckoutRepository.findById(1L);
        assertTrue(savedCartCheckout.isPresent());
        assertEquals(cartCheckout, savedCartCheckout.get());
    }

    @Test
    void testFindAllIfEmpty() {
        assertTrue(cartCheckoutRepository.findAll().isEmpty());
    }

    @Test
    void testDeleteCartCheckout() {
        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setCartId(1L);
        cartCheckout.setUserId("user123");
        cartCheckout.setTotalPrice(100.0);
        cartCheckoutRepository.create(cartCheckout);

        assertTrue(cartCheckoutRepository.delete(1L));
        assertTrue(cartCheckoutRepository.findAll().isEmpty());
    }

    @Test
    void testUpdateCartCheckout() {
        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setCartId(1L);
        cartCheckout.setUserId("user123");
        cartCheckout.setTotalPrice(100.0);
        cartCheckoutRepository.create(cartCheckout);

        CartCheckout updatedCartCheckout = new CartCheckout();
        updatedCartCheckout.setCartId(1L);
        updatedCartCheckout.setUserId("updatedUser");
        updatedCartCheckout.setTotalPrice(150.0);
        cartCheckoutRepository.update(1L, updatedCartCheckout);

        Optional<CartCheckout> savedCartCheckout = cartCheckoutRepository.findById(1L);
        assertTrue(savedCartCheckout.isPresent());
        assertEquals("updatedUser", savedCartCheckout.get().getUserId());
        assertEquals(150.0, savedCartCheckout.get().getTotalPrice());
    }
}