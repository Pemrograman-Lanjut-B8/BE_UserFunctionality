package id.ac.ui.cs.advprog.userfunctionality.repository;

import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import id.ac.ui.cs.advprog.userfunctionality.model.CartItems;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class CartCheckoutRepository {
    private List<CartCheckout> cartCheckoutData = new ArrayList<>();

    public CartCheckout create(CartCheckout cartCheckout) {
        cartCheckoutData.add(cartCheckout);
        return cartCheckout;
    }

    public List<CartCheckout> findAll() {
        return cartCheckoutData;
    }

    public Optional<CartCheckout> findById(Long cartId) {
        return cartCheckoutData.stream()
                .filter(cart -> cart.getCartId().equals(cartId))
                .findFirst();
    }

    public CartCheckout update(Long cartId, CartCheckout updatedCartCheckout) {
        for (int i = 0; i < cartCheckoutData.size(); i++) {
            CartCheckout cartCheckout = cartCheckoutData.get(i);
            if (cartCheckout.getCartId().equals(cartId)) {
                // Update cart details
                cartCheckout.setUserId(updatedCartCheckout.getUserId());
                cartCheckout.setItems(updatedCartCheckout.getItems());
                cartCheckout.setTotalPrice(updatedCartCheckout.getTotalPrice());
                return cartCheckout;
            }
        }
        return null;
    }

    public boolean delete(Long cartId) {
        Iterator<CartCheckout> iterator = cartCheckoutData.iterator();
        while (iterator.hasNext()) {
            CartCheckout cartCheckout = iterator.next();
            if (cartCheckout.getCartId().equals(cartId)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
