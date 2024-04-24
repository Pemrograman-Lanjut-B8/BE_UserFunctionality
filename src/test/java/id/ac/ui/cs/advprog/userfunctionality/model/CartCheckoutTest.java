package id.ac.ui.cs.advprog.userfunctionality.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartCheckoutTest {
    private CartCheckout cartCheckout;

    @BeforeEach
    void setUp() {
        this.cartCheckout = new CartCheckout();
        this.cartCheckout.setCartId(1L);
        this.cartCheckout.setUserId("user123");
        this.cartCheckout.setTotalPrice(100.0);

        // Adding items for testing
        CartItems item1 = new CartItems(1L, "Title 1", 50.0, 2);

        this.cartCheckout.getItems().add(item1);
        CartItems item2 = new CartItems(2L, "Title 2", 1000.0, 1);
        this.cartCheckout.getItems().add(item2);
    }

    @Test
    void testGetCartId() {
        assertEquals(1L, this.cartCheckout.getCartId());
    }

    @Test
    void testGetUserId() {
        assertEquals("user123", this.cartCheckout.getUserId());
    }

    @Test
    void testGetTotalPrice() {
        assertEquals(100.0, this.cartCheckout.getTotalPrice());
    }

    @Test
    void testGetItems() {
        assertEquals(2, this.cartCheckout.getItems().size());
    }
}
