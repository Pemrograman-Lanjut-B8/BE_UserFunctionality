package id.ac.ui.cs.advprog.userfunctionality.repository;

import id.ac.ui.cs.advprog.userfunctionality.builder.CartCheckoutBuilder;
import id.ac.ui.cs.advprog.userfunctionality.dto.CartCheckoutDTO;
import id.ac.ui.cs.advprog.userfunctionality.dto.CartItemsDTO;
import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import id.ac.ui.cs.advprog.userfunctionality.model.CartItems;
import id.ac.ui.cs.advprog.userfunctionality.model.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class CartCheckoutRepository {
    private List<CartCheckout> cartCheckoutData = new ArrayList<>();
    private AtomicLong idGenerator = new AtomicLong();

//    blum disambung ke database
    public CartCheckout create(CartCheckoutDTO cartCheckoutDTO, UserEntity user, List<CartItems> items) {
        if (cartCheckoutDTO == null) {
            throw new IllegalArgumentException("CartCheckoutDTO cannot be null");
        }
        CartCheckout cartCheckout = new CartCheckoutBuilder()
                .fromDTO(cartCheckoutDTO, user, items)
                .setId(idGenerator.incrementAndGet())
                .build();
        cartCheckoutData.add(cartCheckout);
        return cartCheckout;
    }

    public List<CartCheckoutDTO> findAll() {
        return cartCheckoutData.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CartCheckoutDTO> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cart ID cannot be null");
        }
        return cartCheckoutData.stream()
                .filter(cart -> cart.getId() == id)
                .map(this::convertToDTO)
                .findFirst();
    }

    public CartCheckout update(Long id, CartCheckoutDTO updatedCartCheckoutDTO, UserEntity user, List<CartItems> items) {
        if (id == null || updatedCartCheckoutDTO == null) {
            throw new IllegalArgumentException("Cart ID and CartCheckoutDTO cannot be null");
        }
        return cartCheckoutData.stream()
                .filter(cart -> cart.getId() == id)
                .findFirst()
                .map(cart -> {
                    CartCheckout updatedCartCheckout = new CartCheckoutBuilder()
                            .fromDTO(updatedCartCheckoutDTO, user, items)
                            .setId(cart.getId())
                            .build();
                    int index = cartCheckoutData.indexOf(cart);
                    cartCheckoutData.set(index, updatedCartCheckout);
                    return updatedCartCheckout;
                })
                .orElseThrow(() -> new IllegalArgumentException("CartCheckout not found with id: " + id));
    }

    public boolean delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cart ID cannot be null");
        }
        return cartCheckoutData.removeIf(cart -> cart.getId() == id);
    }

    private CartCheckoutDTO convertToDTO(CartCheckout cartCheckout) {
        CartCheckoutDTO dto = new CartCheckoutDTO();
        dto.setId(cartCheckout.getId());
        dto.setUserId(cartCheckout.getUser().getId().toString());
        dto.setItems(cartCheckout.getItems().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()));
        dto.setTotalPrice(cartCheckout.getTotalPrice());
        dto.setStatus(cartCheckout.getStatus());
        return dto;
    }

    private CartItemsDTO convertToDTO(CartItems cartItems) {
        CartItemsDTO dto = new CartItemsDTO();
        dto.setCartId(cartItems.getId());
        dto.setBookIsbn(cartItems.getBook().getIsbn());
        dto.setBookTitle(cartItems.getBook().getJudulBuku());
        dto.setPrice(cartItems.getBook().getHarga());
        dto.setQuantity(cartItems.getQuantity());
        return dto;
    }
}