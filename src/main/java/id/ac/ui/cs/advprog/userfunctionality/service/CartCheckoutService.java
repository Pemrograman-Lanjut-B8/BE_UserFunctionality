package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.dto.CartCheckoutDTO;

import java.util.List;

public interface CartCheckoutService {
    CartCheckoutDTO createCartCheckout(CartCheckoutDTO cartCheckoutDTO);
    List<CartCheckoutDTO> findAll();
    CartCheckoutDTO findCartCheckoutById(Long cartId);
    CartCheckoutDTO updateCartCheckout(Long cartId, CartCheckoutDTO cartCheckoutDTO);
    boolean deleteCartCheckout(Long cartId);
}
