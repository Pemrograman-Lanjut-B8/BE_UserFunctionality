package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.builder.CartCheckoutBuilder;
import id.ac.ui.cs.advprog.userfunctionality.builder.CartItemsBuilder;
import id.ac.ui.cs.advprog.userfunctionality.dto.CartCheckoutDTO;
import id.ac.ui.cs.advprog.userfunctionality.dto.CartItemsDTO;
import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import id.ac.ui.cs.advprog.userfunctionality.model.CartItems;
import id.ac.ui.cs.advprog.userfunctionality.model.UserEntity;
import id.ac.ui.cs.advprog.userfunctionality.repository.CartCheckoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CartCheckoutServiceImplTest {

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

        UserEntity user = new UserEntity();
        user.setId(UUID.fromString(cartCheckoutDTO.getUserId()));

        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setId(1L);
        cartCheckout.setUser(user);
        cartCheckout.setItems(Collections.emptyList());

        when(cartCheckoutRepository.save(any(CartCheckout.class))).thenReturn(cartCheckout);

        CartCheckoutDTO result = cartCheckoutService.createCartCheckout(cartCheckoutDTO);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testFindAll() {
        UserEntity user = new UserEntity();
        user.setId(UUID.randomUUID());

        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setId(1L);
        cartCheckout.setUser(user);
        cartCheckout.setItems(Collections.emptyList());

        when(cartCheckoutRepository.findAll()).thenReturn(Arrays.asList(cartCheckout));

        List<CartCheckoutDTO> result = cartCheckoutService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    public void testFindCartCheckoutById() {
        UserEntity user = new UserEntity();
        user.setId(UUID.randomUUID());

        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setId(1L);
        cartCheckout.setUser(user);
        cartCheckout.setItems(Collections.emptyList());

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

        UserEntity user = new UserEntity();
        user.setId(UUID.fromString(cartCheckoutDTO.getUserId()));

        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setId(1L);
        cartCheckout.setUser(user);
        cartCheckout.setItems(Collections.emptyList());

        when(cartCheckoutRepository.findById(1L)).thenReturn(Optional.of(cartCheckout));
        when(cartCheckoutRepository.save(any(CartCheckout.class))).thenReturn(cartCheckout);

        CartCheckoutDTO result = cartCheckoutService.updateCartCheckout(1L, cartCheckoutDTO);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testDeleteCartCheckout() {
        Long cartId = 1L;

        when(cartCheckoutRepository.existsById(cartId)).thenReturn(true, false);
        doNothing().when(cartCheckoutRepository).deleteById(cartId);

        boolean result = cartCheckoutService.deleteCartCheckout(cartId);

        assertTrue(result);

        verify(cartCheckoutRepository, times(1)).deleteById(cartId);
        verify(cartCheckoutRepository, times(2)).existsById(cartId);
    }

    @Test
    public void testDeleteCartCheckoutNotFound() {
        Long cartId = 1L;

        when(cartCheckoutRepository.existsById(cartId)).thenReturn(false);

        boolean result = cartCheckoutService.deleteCartCheckout(cartId);

        assertFalse(result);

        verify(cartCheckoutRepository, times(0)).deleteById(cartId);
        verify(cartCheckoutRepository, times(1)).existsById(cartId);
    }

    @Test
    public void testStoreCheckedOutBooks() {
        CartCheckoutDTO cartCheckoutDTO = new CartCheckoutDTO();
        cartCheckoutDTO.setId(1L);
        cartCheckoutDTO.setUserId(UUID.randomUUID().toString());

        UserEntity user = new UserEntity();
        user.setId(UUID.fromString(cartCheckoutDTO.getUserId()));

        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setId(1L);
        cartCheckout.setUser(user);
        cartCheckout.setItems(Collections.emptyList());

        when(cartCheckoutRepository.save(any(CartCheckout.class))).thenReturn(cartCheckout);

        cartCheckoutService.storeCheckedOutBooks(cartCheckoutDTO);
        verify(cartCheckoutRepository, times(1)).save(any(CartCheckout.class));
    }

    @Test
    public void testUpdateCartStatus() {
        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setId(1L);
        cartCheckout.setStatus("Initial Status");

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
