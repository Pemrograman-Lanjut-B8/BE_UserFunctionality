package id.ac.ui.cs.advprog.userfunctionality.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class CartCheckoutTest {
    private CartCheckout cartCheckout;

    @BeforeEach
    void setUp() {
        this.cartCheckout = new CartCheckout();
        // Testing with a UUID to mimic real scenario
        this.cartCheckout.setUserId(UUID.randomUUID().toString());
        this.cartCheckout.setCartId(1L);

        // Adding items for testing
        CartItems item1 = new CartItems(1L, "Title 1", 50.0, 2);
        this.cartCheckout.getItems().add(item1);
        this.cartCheckout.setTotalPrice(50.0 * 2);  // Simulating setting total price when adding items

        CartItems item2 = new CartItems(2L, "Title 2", 1000.0, 1);
        this.cartCheckout.getItems().add(item2);
        this.cartCheckout.setTotalPrice(this.cartCheckout.getTotalPrice() + 1000.0);  // Update total price manually
    }

    @Test
    void testGetCartId() {
        assertEquals(1L, this.cartCheckout.getCartId());
    }

    @Test
    void testGetUserId() {
        assertNotNull(this.cartCheckout.getUserId());  // Just check it's not null, because it's a UUID
    }

    @Test
    void testGetTotalPrice() {
        assertEquals(1100.0, this.cartCheckout.getTotalPrice());  // Reflects adding both items
    }

    @Test
    void testGetItems() {
        assertEquals(2, this.cartCheckout.getItems().size());
    }
}
