package id.ac.ui.cs.advprog.userfunctionality.repository;

import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class CartCheckoutRepository {
    private List<CartCheckout> cartCheckoutData = new ArrayList<>();

    public CartCheckout create(CartCheckout cartCheckout) {
        if (cartCheckout == null) {
            throw new IllegalArgumentException("CartCheckout cannot be null");
        }
        cartCheckoutData.add(cartCheckout);
        return cartCheckout;
    }

    public List<CartCheckout> findAll() {
        return new ArrayList<>(cartCheckoutData);
    }

    public Optional<CartCheckout> findById(Long cartId) {
        if (cartId == null) {
            throw new IllegalArgumentException("Cart ID cannot be null");
        }
        return cartCheckoutData.stream()
                .filter(cart -> cart.getCartId().equals(cartId))
                .findFirst();
    }

    public CartCheckout update(Long cartId, CartCheckout updatedCartCheckout) {
        if (cartId == null || updatedCartCheckout == null) {
            throw new IllegalArgumentException("Cart ID and CartCheckout cannot be null");
        }
        return cartCheckoutData.stream()
                .filter(cart -> cart.getCartId().equals(cartId))
                .findFirst()
                .map(cart -> {
                    cart.setUserId(updatedCartCheckout.getUserId());
                    cart.setItems(updatedCartCheckout.getItems());
                    cart.setTotalPrice(updatedCartCheckout.getTotalPrice());
                    return cart;
                })
                .orElseThrow(() -> new IllegalArgumentException("CartCheckout not found with id: " + cartId));
    }

    public boolean delete(Long cartId) {
        if (cartId == null) {
            throw new IllegalArgumentException("Cart ID cannot be null");
        }
        return cartCheckoutData.removeIf(cart -> cart.getCartId().equals(cartId));
    }
}
