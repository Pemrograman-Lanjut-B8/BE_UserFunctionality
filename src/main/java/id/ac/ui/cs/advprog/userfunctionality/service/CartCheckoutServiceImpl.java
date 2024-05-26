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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartCheckoutServiceImpl implements CartCheckoutService {

    @Autowired
    private CartCheckoutRepository cartCheckoutRepository;

    @Override
    public CartCheckoutDTO createCartCheckout(CartCheckoutDTO cartCheckoutDTO) {
        UserEntity user = getUserEntity(cartCheckoutDTO.getUserId());
        List<CartItems> items = cartCheckoutDTO.getItems().stream()
                .map(dto -> toCartItemsEntity(dto, user))
                .collect(Collectors.toList());
        CartCheckout cartCheckout = new CartCheckoutBuilder()
                .fromDTO(cartCheckoutDTO, user, items)
                .build();
        CartCheckout savedCheckout = cartCheckoutRepository.save(cartCheckout);
        return toCartCheckoutDTO(savedCheckout);
    }

    @Override
    public List<CartCheckoutDTO> findAll() {
        return cartCheckoutRepository.findAll().stream()
                .map(this::toCartCheckoutDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CartCheckoutDTO findCartCheckoutById(Long checkoutId) {
        return cartCheckoutRepository.findById(checkoutId)
                .map(this::toCartCheckoutDTO)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found with id: " + checkoutId));
    }

    @Override
    public CartCheckoutDTO updateCartCheckout(Long checkoutId, CartCheckoutDTO cartCheckoutDTO) {
        UserEntity user = getUserEntity(cartCheckoutDTO.getUserId());
        List<CartItems> items = cartCheckoutDTO.getItems().stream()
                .map(dto -> toCartItemsEntity(dto, user))
                .collect(Collectors.toList());
        CartCheckout updatedCartCheckout = new CartCheckoutBuilder()
                .fromDTO(cartCheckoutDTO, user, items)
                .setId(checkoutId)
                .build();
        CartCheckout savedCheckout = cartCheckoutRepository.save(updatedCartCheckout);
        return toCartCheckoutDTO(savedCheckout);
    }

    @Override
    public boolean deleteCartCheckout(Long checkoutId) {
        if (!cartCheckoutRepository.existsById(checkoutId)) {
            return false;
        }
        cartCheckoutRepository.deleteById(checkoutId);
        return !cartCheckoutRepository.existsById(checkoutId);
    }

    @Override
    public void storeCheckedOutBooks(CartCheckoutDTO cartCheckoutDTO) {
        UserEntity user = getUserEntity(cartCheckoutDTO.getUserId());
        List<CartItems> items = cartCheckoutDTO.getItems().stream()
                .map(dto -> toCartItemsEntity(dto, user))
                .collect(Collectors.toList());
        CartCheckout cartCheckout = new CartCheckoutBuilder()
                .fromDTO(cartCheckoutDTO, user, items)
                .build();
        cartCheckoutRepository.save(cartCheckout);
    }

    @Override
    public boolean updateCartStatus(Long checkoutId, String status) {
        return cartCheckoutRepository.findById(checkoutId)
                .map(cartCheckout -> {
                    cartCheckout.setStatus(status);
                    cartCheckoutRepository.save(cartCheckout);
                    return true;
                })
                .orElse(false);
    }

    private UserEntity getUserEntity(String userId) {
        UserEntity user = new UserEntity();
        user.setId(UUID.fromString(userId));
        return user;
    }

    private CartCheckoutDTO toCartCheckoutDTO(CartCheckout cartCheckout) {
        CartCheckoutDTO dto = new CartCheckoutDTO();
        dto.setId(cartCheckout.getId());
        UUID userId = cartCheckout.getUser() != null ? cartCheckout.getUser().getId() : null;
        dto.setUserId(userId != null ? userId.toString() : null);
        dto.setItems(cartCheckout.getItems() != null ? cartCheckout.getItems().stream()
                .map(this::toCartItemsDTO)
                .collect(Collectors.toList()) : null);
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
