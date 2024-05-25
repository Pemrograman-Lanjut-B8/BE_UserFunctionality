package id.ac.ui.cs.advprog.userfunctionality.controller;

import id.ac.ui.cs.advprog.userfunctionality.dto.CartCheckoutDTO;
import id.ac.ui.cs.advprog.userfunctionality.service.CartCheckoutService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CartCheckoutControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CartCheckoutService cartCheckoutService;

    @InjectMocks
    private CartCheckoutController cartCheckoutController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartCheckoutController)
                .build();
    }

    @Test
    public void testGetCartCheckout() throws Exception {
        CartCheckoutDTO cartCheckoutDTO = new CartCheckoutDTO();
        cartCheckoutDTO.setId(1L);
        cartCheckoutDTO.setUserId("user123");
        cartCheckoutDTO.setTotalPrice(100.0);

        when(cartCheckoutService.findCartCheckoutById(1L))
                .thenReturn(CompletableFuture.completedFuture(cartCheckoutDTO));

        mockMvc.perform(get("/cart/checkout/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.userId").value("user123"))
                .andExpect(jsonPath("$.totalPrice").value(100.0));
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
        cartCheckoutDTO.setId(1L);
        cartCheckoutDTO.setUserId("user123");

        when(cartCheckoutService.createCartCheckout(any(CartCheckoutDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(cartCheckoutDTO));

        String cartCheckoutJson = "{\"id\":1, \"userId\":\"user123\", \"totalPrice\":100.0, \"status\":\"Pending\", \"items\":[]}";

        mockMvc.perform(post("/cart/createCart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cartCheckoutJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.userId").value("user123"));
    }

    @Test
    public void testCartCheckoutList() throws Exception {
        List<CartCheckoutDTO> allCartCheckouts = new ArrayList<>();
        CartCheckoutDTO cartCheckoutDTO = new CartCheckoutDTO();
        cartCheckoutDTO.setId(1L);
        allCartCheckouts.add(cartCheckoutDTO);

        when(cartCheckoutService.findAll())
                .thenReturn(CompletableFuture.completedFuture(allCartCheckouts));

        mockMvc.perform(get("/cart/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    public void testEditCartCheckout() throws Exception {
        CartCheckoutDTO cartCheckoutDTO = new CartCheckoutDTO();
        cartCheckoutDTO.setId(1L);
        cartCheckoutDTO.setUserId("updatedUser");

        when(cartCheckoutService.updateCartCheckout(eq(1L), any(CartCheckoutDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(cartCheckoutDTO));

        String cartCheckoutJson = "{\"id\":1, \"userId\":\"updatedUser\", \"totalPrice\":100.0, \"status\":\"Pending\", \"items\":[]}";

        mockMvc.perform(put("/cart/edit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cartCheckoutJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.userId").value("updatedUser"));
    }

    @Test
    public void testEditCartCheckoutNotFound() throws Exception {
        when(cartCheckoutService.updateCartCheckout(eq(1L), any(CartCheckoutDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(null));

        String cartCheckoutJson = "{\"id\":1, \"userId\":\"updatedUser\", \"totalPrice\":100.0, \"status\":\"Pending\", \"items\":[]}";

        mockMvc.perform(put("/cart/edit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cartCheckoutJson))
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

    @Test
    public void testStoreCheckedOutBooks() throws Exception {
        when(cartCheckoutService.storeCheckedOutBooks(any(CartCheckoutDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(null));

        String cartCheckoutJson = "{\"id\":1, \"userId\":\"user123\", \"totalPrice\":100.0, \"status\":\"Pending\", \"items\":[]}";

        mockMvc.perform(post("/cart/inventory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cartCheckoutJson))
                .andExpect(status().isCreated());
    }
}
