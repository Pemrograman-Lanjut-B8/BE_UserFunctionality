package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.dto.CartCheckoutDTO;
import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import id.ac.ui.cs.advprog.userfunctionality.repository.CartCheckoutRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartCheckoutServiceTest {

    @Mock
    private CartCheckoutRepository cartCheckoutRepository;

    @InjectMocks
    private CartCheckoutService cartCheckoutService = new CartCheckoutServiceImpl();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCartCheckout() {
        CartCheckoutDTO cartCheckoutDTO = new CartCheckoutDTO();
        cartCheckoutDTO.setCartId(1L);
        cartCheckoutDTO.setUserId("user123");
        cartCheckoutDTO.setTotalPrice(100.0);

        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setCartId(cartCheckoutDTO.getCartId());
        cartCheckout.setUserId(cartCheckoutDTO.getUserId());
        cartCheckout.setTotalPrice(cartCheckoutDTO.getTotalPrice());

        when(cartCheckoutRepository.create(any())).thenReturn(cartCheckout);

        CartCheckoutDTO createdCartCheckoutDTO = cartCheckoutService.createCartCheckout(cartCheckoutDTO);

        assertEquals("user123", createdCartCheckoutDTO.getUserId());
    }

    @Test
    public void testFindCartCheckoutById() {
        Long cartId = 1L;
        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setCartId(1L);
        cartCheckout.setUserId("user123");
        cartCheckout.setTotalPrice(100.0);

        CartCheckoutDTO cartCheckoutDTO = new CartCheckoutDTO();
        cartCheckoutDTO.setCartId(cartCheckout.getCartId());
        cartCheckoutDTO.setUserId(cartCheckout.getUserId());
        cartCheckoutDTO.setTotalPrice(cartCheckout.getTotalPrice());

        when(cartCheckoutRepository.findById(cartId)).thenReturn(Optional.of(cartCheckout));

        CartCheckoutDTO foundCartCheckoutDTO = cartCheckoutService.findCartCheckoutById(cartId);

        assertNotNull(foundCartCheckoutDTO);
        assertEquals("user123", foundCartCheckoutDTO.getUserId());
    }

    @Test
    public void testUpdateCartCheckout() {
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
        when(cartCheckoutRepository.update(eq(cartId), any(CartCheckout.class))).thenReturn(existingCartCheckout);

        existingCartCheckout.setUserId(updateDTO.getUserId());
        existingCartCheckout.setTotalPrice(updateDTO.getTotalPrice());

        CartCheckoutDTO updatedCartCheckout = cartCheckoutService.updateCartCheckout(cartId, updateDTO);

        assertNotNull(updatedCartCheckout);
        assertEquals("updatedUser123", updatedCartCheckout.getUserId());
        assertEquals(150.0, updatedCartCheckout.getTotalPrice());
    }

    @Test
    public void testDeleteCartCheckout() {
        Long cartId = 1L;
        when(cartCheckoutRepository.delete(cartId)).thenReturn(true);

        boolean result = cartCheckoutService.deleteCartCheckout(cartId);

        assertTrue(result);
        verify(cartCheckoutRepository).delete(cartId);
    }

    @Test
    public void testDeleteCartCheckoutNotFound() {
        Long cartId = 99L;
        when(cartCheckoutRepository.delete(cartId)).thenReturn(false);

        boolean result = cartCheckoutService.deleteCartCheckout(cartId);

        assertFalse(result);
    }
}
