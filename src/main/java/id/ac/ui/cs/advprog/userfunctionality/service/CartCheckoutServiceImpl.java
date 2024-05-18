package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.dto.CartCheckoutDTO;
import id.ac.ui.cs.advprog.userfunctionality.dto.CartItemsDTO;
import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import id.ac.ui.cs.advprog.userfunctionality.model.CartItems;
import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.model.UserEntity;
import id.ac.ui.cs.advprog.userfunctionality.repository.CartCheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class CartCheckoutServiceImpl implements CartCheckoutService {

    @Autowired
    private CartCheckoutRepository cartCheckoutRepository;

    @Override
    @Async
    public CompletableFuture<CartCheckoutDTO> createCartCheckout(CartCheckoutDTO cartCheckoutDTO) {
        return CompletableFuture.supplyAsync(() -> {
            CartCheckout cartCheckout = toCartCheckoutEntity(cartCheckoutDTO);
            CartCheckout savedCheckout = cartCheckoutRepository.create(cartCheckout);
            return toCartCheckoutDTO(savedCheckout);
        });
    }

    @Override
    @Async
    public CompletableFuture<List<CartCheckoutDTO>> findAll() {
        return CompletableFuture.supplyAsync(() -> cartCheckoutRepository.findAll().stream()
                .map(this::toCartCheckoutDTO)
                .collect(Collectors.toList()));
    }

    @Override
    @Async
    public CompletableFuture<CartCheckoutDTO> findCartCheckoutById(Long cartId) {
        return CompletableFuture.supplyAsync(() -> {
            CartCheckout cartCheckout = cartCheckoutRepository.findById(cartId)
                    .orElseThrow(() -> new IllegalArgumentException("Cart not found with id: " + cartId));
            return toCartCheckoutDTO(cartCheckout);
        });
    }

    @Override
    @Async
    public CompletableFuture<CartCheckoutDTO> updateCartCheckout(Long cartId, CartCheckoutDTO cartCheckoutDTO) {
        return CompletableFuture.supplyAsync(() -> {
            CartCheckout cartCheckout = toCartCheckoutEntity(cartCheckoutDTO);
            cartCheckout.setId(cartId);
            CartCheckout updatedCheckout = cartCheckoutRepository.update(cartId, cartCheckout);
            return toCartCheckoutDTO(updatedCheckout);
        });
    }

    @Override
    @Async
    public CompletableFuture<Boolean> deleteCartCheckout(Long cartId) {
        return CompletableFuture.supplyAsync(() -> cartCheckoutRepository.delete(cartId));
    }

    @Override
    @Async
    public CompletableFuture<Void> storeCheckedOutBooks(CartCheckoutDTO cartCheckoutDTO) {
        return CompletableFuture.runAsync(() -> {
            cartCheckoutDTO.getItems().forEach(item -> {
                System.out.println("Judul Buku: " + item.getBookTitle());
                System.out.println("Jumlah: " + item.getQuantity());
            });
        });
    }

    // Helper method to convert DTO to entity
    private CartCheckout toCartCheckoutEntity(CartCheckoutDTO dto) {
        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setId(dto.getId());
        cartCheckout.setUser(new UserEntity(dto.getUserId(), null, null));
        cartCheckout.setItems(dto.getItems().stream()
                .map(this::toCartItemsEntity)
                .collect(Collectors.toList()));
        cartCheckout.setTotalPrice(dto.getTotalPrice());
        cartCheckout.setStatus(dto.getStatus());
        return cartCheckout;
    }

    // Helper method to convert entity to DTO
    private CartCheckoutDTO toCartCheckoutDTO(CartCheckout cartCheckout) {
        CartCheckoutDTO dto = new CartCheckoutDTO();
        dto.setId(cartCheckout.getId());
        dto.setUserId(cartCheckout.getUser().getId().toString());
        dto.setItems(cartCheckout.getItems().stream()
                .map(this::toCartItemsDTO)
                .collect(Collectors.toList()));
        dto.setTotalPrice(cartCheckout.getTotalPrice());
        dto.setStatus(cartCheckout.getStatus());
        return dto;
    }

    // Helper method to convert CartItemsDTO to CartItems entity
    private CartItems toCartItemsEntity(CartItemsDTO dto) {
        CartItems cartItems = new CartItems();
        cartItems.setId(dto.getCartId());
        Book book = new Book();
        book.setIsbn(dto.getBookIsbn());
        cartItems.setBook(book);
        cartItems.setQuantity(dto.getQuantity());
        return cartItems;
    }

    // Helper method to convert CartItems entity to CartItemsDTO
    private CartItemsDTO toCartItemsDTO(CartItems cartItems) {
        CartItemsDTO dto = new CartItemsDTO();
        dto.setCartId(cartItems.getId());
        dto.setBookIsbn(cartItems.getBook().getIsbn());
        dto.setBookTitle(cartItems.getBook().getJudulBuku());
        dto.setPrice(cartItems.getBook().getHarga());
        dto.setQuantity(cartItems.getQuantity());
        return dto;
    }
}