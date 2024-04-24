package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import id.ac.ui.cs.advprog.userfunctionality.model.CartItems;

import java.util.List;
import java.util.Optional;

public interface CartCheckoutService {
    CartCheckout create(CartCheckout cartCheckout);
    List<CartCheckout> findAll();
    Optional<CartCheckout> findById(Long cartId);
    CartCheckout update(Long cartId, CartCheckout updatedCartCheckout);
    boolean delete(Long cartId);
}
