package id.ac.ui.cs.advprog.userfunctionality.controller;

import id.ac.ui.cs.advprog.userfunctionality.dto.CartCheckoutDTO;
import id.ac.ui.cs.advprog.userfunctionality.service.CartCheckoutService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CartCheckoutControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CartCheckoutService cartCheckoutService;

    @InjectMocks
    private CartCheckoutController cartCheckoutController;

    @BeforeEach
    public void setUp() {
        mockMvc = standaloneSetup(cartCheckoutController).build();
    }

    @Test
    public void testGetCartCheckout() throws Exception {
        CartCheckoutDTO cartCheckoutDTO = new CartCheckoutDTO();
        cartCheckoutDTO.setCartId(1L);
        when(cartCheckoutService.findCartCheckoutById(1L))
                .thenReturn(CompletableFuture.completedFuture(cartCheckoutDTO));

        mockMvc.perform(get("/cart/checkout/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartId").value(1L));
    }

    @Test
    public void testGetCartCheckoutNotFound() throws Exception {
        when(cartCheckoutService.findCartCheckoutById(1L))
                .thenReturn(CompletableFuture.completedFuture(null));

        mockMvc.perform(get("/cart/checkout/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateCartCheckout() throws Exception {
        CartCheckoutDTO cartCheckoutDTO = new CartCheckoutDTO();
        cartCheckoutDTO.setCartId(1L);
        when(cartCheckoutService.createCartCheckout(any(CartCheckoutDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(cartCheckoutDTO));

        mockMvc.perform(post("/cart/createCart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cartId\":1, \"userId\":\"user123\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cartId").value(1L));
    }

    @Test
    public void testCartCheckoutList() throws Exception {
        List<CartCheckoutDTO> allCartCheckouts = new ArrayList<>();
        allCartCheckouts.add(new CartCheckoutDTO());
        when(cartCheckoutService.findAll())
                .thenReturn(CompletableFuture.completedFuture(allCartCheckouts));

        mockMvc.perform(get("/cart/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void testEditCartCheckout() throws Exception {
        CartCheckoutDTO cartCheckoutDTO = new CartCheckoutDTO();
        cartCheckoutDTO.setCartId(1L);
        when(cartCheckoutService.updateCartCheckout(eq(1L), any(CartCheckoutDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(cartCheckoutDTO));

        mockMvc.perform(put("/cart/edit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cartId\":1, \"userId\":\"updatedUser\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartId").value(1L));
    }

    @Test
    public void testEditCartCheckoutNotFound() throws Exception {
        when(cartCheckoutService.updateCartCheckout(eq(1L), any(CartCheckoutDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(null));

        mockMvc.perform(put("/cart/edit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cartId\":1, \"userId\":\"updatedUser\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteCartCheckout() throws Exception {
        when(cartCheckoutService.deleteCartCheckout(1L))
                .thenReturn(CompletableFuture.completedFuture(true));

        mockMvc.perform(delete("/cart/delete/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteCartCheckoutNotFound() throws Exception {
        when(cartCheckoutService.deleteCartCheckout(1L))
                .thenReturn(CompletableFuture.completedFuture(false));

        mockMvc.perform(delete("/cart/delete/1"))
                .andExpect(status().isNotFound());
    }
}
