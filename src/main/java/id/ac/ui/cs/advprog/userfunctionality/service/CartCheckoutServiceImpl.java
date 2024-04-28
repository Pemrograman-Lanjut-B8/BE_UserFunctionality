package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import id.ac.ui.cs.advprog.userfunctionality.repository.CartCheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartCheckoutServiceImpl implements CartCheckoutService {

    @Autowired
    private CartCheckoutRepository cartCheckoutRepository;

    @Override
    public CartCheckout create(CartCheckout cartCheckout) {
        return cartCheckoutRepository.create(cartCheckout);
    }

    @Override
    public List<CartCheckout> findAll() {
        return cartCheckoutRepository.findAll();
    }

    @Override
    public Optional<CartCheckout> findById(Long cartId) {
        return cartCheckoutRepository.findById(cartId);
    }

    @Override
    public CartCheckout update(Long cartId, CartCheckout updatedCartCheckout) {
        Optional<CartCheckout> existingCartCheckout = cartCheckoutRepository.findById(cartId);
        if (existingCartCheckout.isPresent()) {
            CartCheckout cartCheckout = existingCartCheckout.get();
            cartCheckout.setUserId(updatedCartCheckout.getUserId());
            cartCheckout.setItems(updatedCartCheckout.getItems());
            cartCheckout.setTotalPrice(updatedCartCheckout.getTotalPrice());
            return cartCheckoutRepository.update(cartId, cartCheckout);
        }
        return null;
    }

    @Override
    public boolean delete(Long cartId) {
        return cartCheckoutRepository.delete(cartId);
    }
}
