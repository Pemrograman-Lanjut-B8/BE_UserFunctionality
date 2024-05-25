package id.ac.ui.cs.advprog.userfunctionality.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CartCheckoutTest {
    private CartCheckout cartCheckout;

    @BeforeEach
    void setUp() {
        this.cartCheckout = new CartCheckout();

        UserEntity user = new UserEntity();
        user.setId(UUID.randomUUID());
        this.cartCheckout.setUser(user);

        List<CartItems> items = new ArrayList<>();
        CartItems item1 = new CartItems();
        item1.setId(1L);
        item1.setQuantity(2);

        Book book1 = new Book();
        book1.setJudulBuku("Title 1");
        book1.setHarga(50.0);
        item1.setBook(book1);
        items.add(item1);

        CartItems item2 = new CartItems();
        item2.setId(2L);
        item2.setQuantity(1);

        Book book2 = new Book();
        book2.setJudulBuku("Title 2");
        book2.setHarga(1000.0);
        item2.setBook(book2);
        items.add(item2);

        this.cartCheckout.setItems(items);
        this.cartCheckout.setTotalPrice(50.0 * 2 + 1000.0);
        this.cartCheckout.setStatus("Pending");
    }

    @Test
    void testGetId() {
        assertEquals(0L, this.cartCheckout.getId());
    }

    @Test
    void testGetUser() {
        assertNotNull(this.cartCheckout.getUser());
    }

    @Test
    void testGetTotalPrice() {
        assertEquals(1100.0, this.cartCheckout.getTotalPrice());
    }

    @Test
    void testGetItems() {
        assertEquals(2, this.cartCheckout.getItems().size());
    }

    @Test
    void testGetStatus() {
        assertEquals("Pending", this.cartCheckout.getStatus());
    }
}
