package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.dto.CartCheckoutDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CartCheckoutService {
    CompletableFuture<CartCheckoutDTO> createCartCheckout(CartCheckoutDTO cartCheckoutDTO);
    CompletableFuture<List<CartCheckoutDTO>> findAll();
    CompletableFuture<CartCheckoutDTO> findCartCheckoutById(Long cartId);
    CompletableFuture<CartCheckoutDTO> updateCartCheckout(Long cartId, CartCheckoutDTO cartCheckoutDTO);
    CompletableFuture<Boolean> deleteCartCheckout(Long cartId);
}
