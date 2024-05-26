package id.ac.ui.cs.advprog.userfunctionality.builder;

import id.ac.ui.cs.advprog.userfunctionality.dto.CartItemsDTO;
import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.model.CartItems;
import id.ac.ui.cs.advprog.userfunctionality.model.UserEntity;

public class CartItemsBuilder {
    private long id;
    private Book book;
    private int quantity;
    private UserEntity user;

    public CartItemsBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public CartItemsBuilder setBook(Book book) {
        this.book = book;
        return this;
    }

    public CartItemsBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public CartItemsBuilder setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public CartItemsBuilder fromDTO(CartItemsDTO dto, Book book, UserEntity user) {
        this.id = dto.getCartId();
        this.book = book;
        this.quantity = dto.getQuantity();
        this.user = user;
        return this;
    }

    public CartItems build() {
        CartItems cartItems = new CartItems();
        cartItems.setId(this.id);
        cartItems.setBook(this.book);
        cartItems.setQuantity(this.quantity);
        cartItems.setUser(this.user);
        return cartItems;
    }
}