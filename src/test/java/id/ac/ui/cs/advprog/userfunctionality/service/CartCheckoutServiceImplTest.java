package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import id.ac.ui.cs.advprog.userfunctionality.model.CartItems;
import id.ac.ui.cs.advprog.userfunctionality.repository.CartCheckoutRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartCheckoutServiceImplTest {

    @Mock
    private CartCheckoutRepository cartCheckoutRepository;

    @InjectMocks
    private CartCheckoutServiceImpl cartCheckoutService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCartCheckout() {
        // Prepare dummy data
        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setCartId(1L);
        cartCheckout.setUserId("user123");
        cartCheckout.setTotalPrice(100.0);
        List<CartItems> items = new ArrayList<>();
        // Add some items if needed
        cartCheckout.setItems(items);

        // Mock the behavior of the CartCheckoutRepository
        when(cartCheckoutRepository.create(cartCheckout)).thenReturn(cartCheckout);

        // Call the method under test
        CartCheckout createdCartCheckout = cartCheckoutService.create(cartCheckout);

        // Verify the result
        assertEquals(cartCheckout, createdCartCheckout);
    }

    @Test
    public void testFindAll() {
        // Prepare dummy data
        List<CartCheckout> expectedCartCheckouts = new ArrayList<>();
        // Add some CartCheckouts if needed

        // Mock the behavior of the CartCheckoutRepository
        when(cartCheckoutRepository.findAll()).thenReturn(expectedCartCheckouts);

        // Call the method under test
        List<CartCheckout> actualCartCheckouts = cartCheckoutService.findAll();

        // Verify the result
        assertEquals(expectedCartCheckouts, actualCartCheckouts);
    }

    @Test
    public void testFindById() {
        // Prepare dummy data
        Long cartId = 1L;
        CartCheckout expectedCartCheckout = new CartCheckout();
        // Set properties of expectedCartCheckout if needed

        // Mock the behavior of the CartCheckoutRepository
        when(cartCheckoutRepository.findById(cartId)).thenReturn(Optional.of(expectedCartCheckout));

        // Call the method under test
        Optional<CartCheckout> actualCartCheckout = cartCheckoutService.findById(cartId);

        // Verify the result
        assertTrue(actualCartCheckout.isPresent());
        assertEquals(expectedCartCheckout, actualCartCheckout.get());
    }

    @Test
    public void testUpdate() {
        // Prepare dummy data
        Long cartId = 1L;
        CartCheckout updatedCartCheckout = new CartCheckout();
        // Set properties of updatedCartCheckout if needed

        // Mock the behavior of the CartCheckoutRepository
        when(cartCheckoutRepository.findById(cartId)).thenReturn(Optional.of(updatedCartCheckout));
        when(cartCheckoutRepository.update(cartId, updatedCartCheckout)).thenReturn(updatedCartCheckout);

        // Call the method under test
        CartCheckout returnedCartCheckout = cartCheckoutService.update(cartId, updatedCartCheckout);

        // Verify the result
        assertEquals(updatedCartCheckout, returnedCartCheckout);
    }

    @Test
    public void testDelete() {
        // Prepare dummy data
        Long cartId = 1L;

        // Mock the behavior of the CartCheckoutRepository
        when(cartCheckoutRepository.delete(cartId)).thenReturn(true);

        // Call the method under test
        boolean isDeleted = cartCheckoutService.delete(cartId);

        // Verify the result
        assertTrue(isDeleted);
        verify(cartCheckoutRepository).delete(cartId);
    }
}

