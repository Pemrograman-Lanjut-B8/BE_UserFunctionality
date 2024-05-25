package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.dto.CartCheckoutDTO;
import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import id.ac.ui.cs.advprog.userfunctionality.model.UserEntity;
import id.ac.ui.cs.advprog.userfunctionality.repository.CartCheckoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CartCheckoutServiceTest {

    @Mock
    private CartCheckoutRepository cartCheckoutRepository;

    @InjectMocks
    private CartCheckoutServiceImpl cartCheckoutService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCartCheckout() {
        CartCheckoutDTO cartCheckoutDTO = new CartCheckoutDTO();
        cartCheckoutDTO.setId(1L);
        cartCheckoutDTO.setUserId(UUID.randomUUID().toString());

        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setId(1L);
        cartCheckout.setUser(new UserEntity());

        when(cartCheckoutRepository.save(any(CartCheckout.class))).thenReturn(cartCheckout);

        CartCheckoutDTO result = cartCheckoutService.createCartCheckout(cartCheckoutDTO);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testFindAll() {
        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setId(1L);

        when(cartCheckoutRepository.findAll()).thenReturn(Arrays.asList(cartCheckout));

        List<CartCheckoutDTO> result = cartCheckoutService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    public void testFindCartCheckoutById() {
        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setId(1L);

        when(cartCheckoutRepository.findById(1L)).thenReturn(Optional.of(cartCheckout));

        CartCheckoutDTO result = cartCheckoutService.findCartCheckoutById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testFindCartCheckoutByIdNotFound() {
        when(cartCheckoutRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            cartCheckoutService.findCartCheckoutById(1L);
        });
    }

    @Test
    public void testUpdateCartCheckout() {
        CartCheckoutDTO cartCheckoutDTO = new CartCheckoutDTO();
        cartCheckoutDTO.setId(1L);
        cartCheckoutDTO.setUserId(UUID.randomUUID().toString());

        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setId(1L);

        when(cartCheckoutRepository.findById(1L)).thenReturn(Optional.of(cartCheckout));
        when(cartCheckoutRepository.save(any(CartCheckout.class))).thenReturn(cartCheckout);

        CartCheckoutDTO result = cartCheckoutService.updateCartCheckout(1L, cartCheckoutDTO);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testDeleteCartCheckout() {
        when(cartCheckoutRepository.existsById(1L)).thenReturn(true, false);
        doNothing().when(cartCheckoutRepository).deleteById(1L);

        boolean result = cartCheckoutService.deleteCartCheckout(1L);
        assertTrue(result);

        verify(cartCheckoutRepository, times(1)).deleteById(1L);
        verify(cartCheckoutRepository, times(2)).existsById(1L);
    }

    @Test
    public void testDeleteCartCheckoutNotFound() {
        when(cartCheckoutRepository.existsById(1L)).thenReturn(false);

        boolean result = cartCheckoutService.deleteCartCheckout(1L);
        assertFalse(result);

        verify(cartCheckoutRepository, never()).deleteById(1L);
        verify(cartCheckoutRepository, times(1)).existsById(1L);
    }

    @Test
    public void testStoreCheckedOutBooks() {
        CartCheckoutDTO cartCheckoutDTO = new CartCheckoutDTO();
        cartCheckoutDTO.setId(1L);
        cartCheckoutDTO.setUserId(UUID.randomUUID().toString());

        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setId(1L);

        when(cartCheckoutRepository.save(any(CartCheckout.class))).thenReturn(cartCheckout);

        cartCheckoutService.storeCheckedOutBooks(cartCheckoutDTO);
        verify(cartCheckoutRepository, times(1)).save(any(CartCheckout.class));
    }

    @Test
    public void testUpdateCartStatus() {
        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setId(1L);

        when(cartCheckoutRepository.findById(1L)).thenReturn(Optional.of(cartCheckout));
        when(cartCheckoutRepository.save(any(CartCheckout.class))).thenReturn(cartCheckout);

        boolean result = cartCheckoutService.updateCartStatus(1L, "Menunggu Konfirmasi Pembayaran");
        assertTrue(result);
    }

    @Test
    public void testUpdateCartStatusNotFound() {
        when(cartCheckoutRepository.findById(1L)).thenReturn(Optional.empty());

        boolean result = cartCheckoutService.updateCartStatus(1L, "Menunggu Konfirmasi Pembayaran");
        assertFalse(result);
    }
}
