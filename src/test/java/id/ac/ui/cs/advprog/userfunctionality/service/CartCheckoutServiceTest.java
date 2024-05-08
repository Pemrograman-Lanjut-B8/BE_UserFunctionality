package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.dto.CartCheckoutDTO;
import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import id.ac.ui.cs.advprog.userfunctionality.repository.CartCheckoutRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CartCheckoutServiceTest {

    @Mock
    private CartCheckoutRepository cartCheckoutRepository;

    @InjectMocks
    private CartCheckoutServiceImpl cartCheckoutService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCartCheckout() throws ExecutionException, InterruptedException {
        CartCheckoutDTO cartCheckoutDTO = new CartCheckoutDTO();
        cartCheckoutDTO.setCartId(1L);
        cartCheckoutDTO.setUserId("user123");
        cartCheckoutDTO.setTotalPrice(100.0);

        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setCartId(cartCheckoutDTO.getCartId());
        cartCheckout.setUserId(cartCheckoutDTO.getUserId());
        cartCheckout.setTotalPrice(cartCheckoutDTO.getTotalPrice());

        when(cartCheckoutRepository.create(any())).thenReturn(cartCheckout);

        CompletableFuture<CartCheckoutDTO> future = cartCheckoutService.createCartCheckout(cartCheckoutDTO);
        CartCheckoutDTO createdCartCheckoutDTO = future.get();

        assertEquals("user123", createdCartCheckoutDTO.getUserId());
        assertEquals(100.0, createdCartCheckoutDTO.getTotalPrice());
    }

    @Test
    public void testFindCartCheckoutById() throws ExecutionException, InterruptedException {
        Long cartId = 1L;
        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setCartId(1L);
        cartCheckout.setUserId("user123");
        cartCheckout.setTotalPrice(100.0);

        when(cartCheckoutRepository.findById(cartId)).thenReturn(Optional.of(cartCheckout));

        CompletableFuture<CartCheckoutDTO> future = cartCheckoutService.findCartCheckoutById(cartId);
        CartCheckoutDTO foundCartCheckoutDTO = future.get();

        assertNotNull(foundCartCheckoutDTO);
        assertEquals("user123", foundCartCheckoutDTO.getUserId());
    }

    @Test
    public void testUpdateCartCheckout() throws ExecutionException, InterruptedException {
        Long cartId = 1L;
        CartCheckout existingCartCheckout = new CartCheckout();
        existingCartCheckout.setCartId(cartId);
        existingCartCheckout.setUserId("user123");
        existingCartCheckout.setTotalPrice(100.0);

        CartCheckoutDTO updateDTO = new CartCheckoutDTO();
        updateDTO.setCartId(cartId);
        updateDTO.setUserId("updatedUser123");
        updateDTO.setTotalPrice(150.0);

        when(cartCheckoutRepository.findById(cartId)).thenReturn(Optional.of(existingCartCheckout));
        when(cartCheckoutRepository.update(eq(cartId), any())).thenReturn(existingCartCheckout);

        existingCartCheckout.setUserId(updateDTO.getUserId());
        existingCartCheckout.setTotalPrice(updateDTO.getTotalPrice());

        CompletableFuture<CartCheckoutDTO> future = cartCheckoutService.updateCartCheckout(cartId, updateDTO);
        CartCheckoutDTO updatedCartCheckout = future.get();

        assertNotNull(updatedCartCheckout);
        assertEquals("updatedUser123", updatedCartCheckout.getUserId());
        assertEquals(150.0, updatedCartCheckout.getTotalPrice());
    }

    @Test
    public void testDeleteCartCheckout() throws ExecutionException, InterruptedException {
        Long cartId = 1L;
        when(cartCheckoutRepository.delete(cartId)).thenReturn(true);

        CompletableFuture<Boolean> future = cartCheckoutService.deleteCartCheckout(cartId);
        boolean result = future.get();

        assertTrue(result);
        verify(cartCheckoutRepository).delete(cartId);
    }

    @Test
    public void testDeleteCartCheckoutNotFound() throws ExecutionException, InterruptedException {
        Long cartId = 99L;
        when(cartCheckoutRepository.delete(cartId)).thenReturn(false);

        CompletableFuture<Boolean> future = cartCheckoutService.deleteCartCheckout(cartId);
        boolean result = future.get();

        assertFalse(result);
    }
}