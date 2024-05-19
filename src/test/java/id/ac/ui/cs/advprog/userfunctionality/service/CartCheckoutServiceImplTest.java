package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.dto.CartCheckoutDTO;
import id.ac.ui.cs.advprog.userfunctionality.dto.CartItemsDTO;
import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import id.ac.ui.cs.advprog.userfunctionality.model.CartItems;
import id.ac.ui.cs.advprog.userfunctionality.repository.CartCheckoutRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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
    public void testCreateCartCheckout() throws Exception {
        // Prepare dummy data
        CartCheckoutDTO cartCheckoutDTO = new CartCheckoutDTO();
        cartCheckoutDTO.setCartId(1L);
        cartCheckoutDTO.setUserId("user123");
        cartCheckoutDTO.setTotalPrice(100.0);
        List<CartItemsDTO> itemsDTO = new ArrayList<>();
        itemsDTO.add(new CartItemsDTO(1L, "Item 1", 50.0, 2));
        cartCheckoutDTO.setItems(itemsDTO);

        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setCartId(1L);
        cartCheckout.setUserId("user123");
        cartCheckout.setTotalPrice(100.0);
        cartCheckout.setItems(itemsDTO.stream()
                .map(dto -> new CartItems(dto.getBookId(), dto.getBookTitle(), dto.getPrice(), dto.getQuantity()))
                .collect(Collectors.toList()));

        // Mock the behavior of the CartCheckoutRepository
        when(cartCheckoutRepository.create(any())).thenReturn(cartCheckout);

        // Call the method under test and wait for the result
        CompletableFuture<CartCheckoutDTO> future = cartCheckoutService.createCartCheckout(cartCheckoutDTO);
        CartCheckoutDTO createdCartCheckoutDTO = future.join();

        // Verify the result
        assertEquals("user123", createdCartCheckoutDTO.getUserId());
        assertEquals(100.0, createdCartCheckoutDTO.getTotalPrice());
    }

    @Test
    public void testFindAll() {
        // Prepare dummy data
        List<CartCheckout> cartCheckouts = new ArrayList<>();
        cartCheckouts.add(new CartCheckout());

        // Mock the behavior of the CartCheckoutRepository
        when(cartCheckoutRepository.findAll()).thenReturn(cartCheckouts);

        // Call the method under test and wait for the result
        CompletableFuture<List<CartCheckoutDTO>> future = cartCheckoutService.findAll();
        List<CartCheckoutDTO> cartCheckoutDTOs = future.join();

        // Verify the result
        assertEquals(cartCheckouts.size(), cartCheckoutDTOs.size());
    }

    @Test
    public void testFindById() {
        Long cartId = 1L;
        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setCartId(cartId);
        cartCheckout.setUserId("user123");

        // Mock findById to return a completed future with the cartCheckout
        when(cartCheckoutRepository.findById(cartId)).thenReturn(Optional.of(cartCheckout));

        // Call the method under test and wait for the result
        CompletableFuture<CartCheckoutDTO> future = cartCheckoutService.findCartCheckoutById(cartId);
        CartCheckoutDTO foundCartCheckoutDTO = future.join();

        // Verify the result
        assertNotNull(foundCartCheckoutDTO);
        assertEquals("user123", foundCartCheckoutDTO.getUserId());
    }

    @Test
    public void testUpdate() {
        Long cartId = 1L;
        CartCheckoutDTO cartCheckoutDTO = new CartCheckoutDTO();
        cartCheckoutDTO.setCartId(cartId);
        cartCheckoutDTO.setUserId("updatedUser");
        cartCheckoutDTO.setTotalPrice(150.0);

        CartCheckout updatedCartCheckout = new CartCheckout();
        updatedCartCheckout.setCartId(cartId);
        updatedCartCheckout.setUserId("updatedUser");
        updatedCartCheckout.setTotalPrice(150.0);

        when(cartCheckoutRepository.update(eq(cartId), any())).thenReturn(updatedCartCheckout);

        // Call the method under test and wait for the result
        CompletableFuture<CartCheckoutDTO> future = cartCheckoutService.updateCartCheckout(cartId, cartCheckoutDTO);
        CartCheckoutDTO updatedCartCheckoutDTO = future.join();

        // Verify the result
        assertEquals("updatedUser", updatedCartCheckoutDTO.getUserId());
    }

    @Test
    public void testDelete() {
        Long cartId = 1L;

        // Mock delete to return a future of true
        when(cartCheckoutRepository.delete(cartId)).thenReturn(true);

        // Call the method under test and wait for the result
        CompletableFuture<Boolean> future = cartCheckoutService.deleteCartCheckout(cartId);
        boolean isDeleted = future.join();

        // Verify
        assertTrue(isDeleted);
        verify(cartCheckoutRepository).delete(cartId);
    }
}