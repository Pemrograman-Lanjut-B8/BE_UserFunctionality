package id.ac.ui.cs.advprog.userfunctionality.repository;

import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CartCheckoutRepository {
    private List<CartCheckout> cartCheckoutData = new ArrayList<>();
    private AtomicLong idGenerator = new AtomicLong();

    public CartCheckout create(CartCheckout cartCheckout) {
        if (cartCheckout == null) {
            throw new IllegalArgumentException("CartCheckout cannot be null");
        }
        cartCheckout.setId(idGenerator.incrementAndGet());
        cartCheckoutData.add(cartCheckout);
        return cartCheckout;
    }

    public List<CartCheckout> findAll() {
        return new ArrayList<>(cartCheckoutData);
    }

    public Optional<CartCheckout> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cart ID cannot be null");
        }
        return cartCheckoutData.stream()
                .filter(cart -> cart.getId() == id)
                .findFirst();
    }

    public CartCheckout update(Long id, CartCheckout updatedCartCheckout) {
        if (id == null || updatedCartCheckout == null) {
            throw new IllegalArgumentException("Cart ID and CartCheckout cannot be null");
        }
        return cartCheckoutData.stream()
                .filter(cart -> cart.getId() == id)
                .findFirst()
                .map(cart -> {
                    cart.setUser(updatedCartCheckout.getUser());
                    cart.setItems(updatedCartCheckout.getItems());
                    cart.setTotalPrice(updatedCartCheckout.getTotalPrice());
                    cart.setStatus(updatedCartCheckout.getStatus());
                    return cart;
                })
                .orElseThrow(() -> new IllegalArgumentException("CartCheckout not found with id: " + id));
    }

    public boolean delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cart ID cannot be null");
        }
        return cartCheckoutData.removeIf(cart -> cart.getId() == id);
    }
}