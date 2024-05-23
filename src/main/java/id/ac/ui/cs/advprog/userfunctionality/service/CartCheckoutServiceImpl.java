package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.builder.CartCheckoutBuilder;
import id.ac.ui.cs.advprog.userfunctionality.builder.CartItemsBuilder;
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
import java.util.UUID;
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
            UserEntity user = getUserEntity(cartCheckoutDTO.getUserId());
            List<CartItems> items = cartCheckoutDTO.getItems().stream()
                    .map(dto -> toCartItemsEntity(dto, user))
                    .collect(Collectors.toList());
            CartCheckout cartCheckout = new CartCheckoutBuilder()
                    .fromDTO(cartCheckoutDTO, user, items)
                    .build();
            CartCheckout savedCheckout = cartCheckoutRepository.save(cartCheckout);
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
        return CompletableFuture.supplyAsync(() -> cartCheckoutRepository.findById(cartId)
                .map(this::toCartCheckoutDTO)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found with id: " + cartId)));
    }

    @Override
    @Async
    public CompletableFuture<CartCheckoutDTO> updateCartCheckout(Long cartId, CartCheckoutDTO cartCheckoutDTO) {
        return CompletableFuture.supplyAsync(() -> {
            UserEntity user = getUserEntity(cartCheckoutDTO.getUserId());
            List<CartItems> items = cartCheckoutDTO.getItems().stream()
                    .map(dto -> toCartItemsEntity(dto, user))
                    .collect(Collectors.toList());
            CartCheckout updatedCartCheckout = new CartCheckoutBuilder()
                    .fromDTO(cartCheckoutDTO, user, items)
                    .setId(cartId)
                    .build();
            CartCheckout savedCheckout = cartCheckoutRepository.save(updatedCartCheckout);
            return toCartCheckoutDTO(savedCheckout);
        });
    }

    @Override
    @Async
    public CompletableFuture<Boolean> deleteCartCheckout(Long cartId) {
        return CompletableFuture.supplyAsync(() -> {
            cartCheckoutRepository.deleteById(cartId);
            return !cartCheckoutRepository.existsById(cartId);
        });
    }

    @Override
    @Async
    public CompletableFuture<Void> storeCheckedOutBooks(CartCheckoutDTO cartCheckoutDTO) {
        return CompletableFuture.runAsync(() -> {
            UserEntity user = getUserEntity(cartCheckoutDTO.getUserId());
            List<CartItems> items = cartCheckoutDTO.getItems().stream()
                    .map(dto -> toCartItemsEntity(dto, user))
                    .collect(Collectors.toList());
            CartCheckout cartCheckout = new CartCheckoutBuilder()
                    .fromDTO(cartCheckoutDTO, user, items)
                    .build();
            cartCheckoutRepository.save(cartCheckout);
        });
    }

    private UserEntity getUserEntity(String userId) {
        UserEntity user = new UserEntity();
        user.setId(UUID.fromString(userId));
        return user;
    }

    private CartCheckoutDTO toCartCheckoutDTO(CartCheckout cartCheckout) {
        CartCheckoutDTO dto = new CartCheckoutDTO();
        dto.setId(cartCheckout.getId());
        UUID userId = cartCheckout.getUser().getId();
        dto.setUserId(userId != null ? userId.toString() : null);
        dto.setItems(cartCheckout.getItems().stream()
                .map(this::toCartItemsDTO)
                .collect(Collectors.toList()));
        dto.setTotalPrice(cartCheckout.getTotalPrice());
        dto.setStatus(cartCheckout.getStatus());
        return dto;
    }

    private CartItems toCartItemsEntity(CartItemsDTO dto, UserEntity user) {
        Book book = getBookByIsbn(dto.getBookIsbn());
        return new CartItemsBuilder()
                .fromDTO(dto, book, user)
                .build();
    }

    private Book getBookByIsbn(String isbn) {
        Book book = new Book();
        book.setIsbn(isbn);
        return book;
    }

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