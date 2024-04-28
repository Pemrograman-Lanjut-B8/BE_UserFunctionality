package id.ac.ui.cs.advprog.userfunctionality.controller;

import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import id.ac.ui.cs.advprog.userfunctionality.service.CartCheckoutService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartCheckoutControllerTest {

    private CartCheckoutController cartCheckoutController;
    private CartCheckoutService cartCheckoutService;
    private Model model;

    @BeforeEach
    public void setUp() {
        cartCheckoutService = mock(CartCheckoutService.class);
        model = mock(Model.class);
        cartCheckoutController = new CartCheckoutController(cartCheckoutService);
    }

    @Test
    public void testCreateCartCheckoutPage() {
        String expectedViewName = "CreateCartCheckout";
        CartCheckout cartCheckout = new CartCheckout();
        when(model.addAttribute("cartCheckout", cartCheckout)).thenReturn(model);

        String viewName = cartCheckoutController.createCartCheckoutPage(model);

        assertEquals(expectedViewName, viewName);
    }

    @Test
    public void testCreateCartCheckoutPost() {
        CartCheckout cartCheckout = new CartCheckout();
        String expectedRedirectUrl = "redirect:list";

        String redirectUrl = cartCheckoutController.createCartCheckoutPost(cartCheckout, model);

        verify(cartCheckoutService, times(1)).create(cartCheckout);
        assertEquals(expectedRedirectUrl, redirectUrl);
    }

    @Test
    public void testCartCheckoutListPage() {
        String expectedViewName = "CartCheckoutList";
        List<CartCheckout> cartCheckoutList = new ArrayList<>();
        when(cartCheckoutService.findAll()).thenReturn(cartCheckoutList);

        String viewName = cartCheckoutController.cartCheckoutListPage(model);

        assertEquals(expectedViewName, viewName);
        verify(model, times(1)).addAttribute("cartCheckouts", cartCheckoutList);
    }

    @Test
    public void testEditCartCheckoutPage() {
        long cartId = 1L;
        String expectedViewName = "EditCartCheckout";
        CartCheckout cartCheckout = new CartCheckout();
        when(cartCheckoutService.findById(cartId)).thenReturn(Optional.of(cartCheckout));
        when(model.addAttribute("cartCheckout", cartCheckout)).thenReturn(model);

        String viewName = cartCheckoutController.editCartCheckoutPage(model, cartId);

        assertEquals(expectedViewName, viewName);
        verify(model, times(1)).addAttribute("cartCheckout", cartCheckout);
    }

    @Test
    public void testEditCartCheckoutPost() {
        long cartId = 1L;
        CartCheckout cartCheckout = new CartCheckout();
        String expectedRedirectUrl = "redirect:/cart/list";

        String redirectUrl = cartCheckoutController.editCartCheckoutPost(cartCheckout, model, cartId);

        verify(cartCheckoutService, times(1)).update(cartId, cartCheckout);
        assertEquals(expectedRedirectUrl, redirectUrl);
    }

    @Test
    public void testDeleteCartCheckoutGet() {
        long cartId = 1L;
        String expectedRedirectUrl = "redirect:/cart/list";

        String redirectUrl = cartCheckoutController.deleteCartCheckoutGet(model, cartId);

        verify(cartCheckoutService, times(1)).delete(cartId);
        assertEquals(expectedRedirectUrl, redirectUrl);
    }
}
