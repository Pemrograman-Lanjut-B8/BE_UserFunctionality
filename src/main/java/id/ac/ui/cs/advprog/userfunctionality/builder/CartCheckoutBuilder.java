package id.ac.ui.cs.advprog.userfunctionality.builder;

import id.ac.ui.cs.advprog.userfunctionality.dto.CartCheckoutDTO;
import id.ac.ui.cs.advprog.userfunctionality.dto.CartItemsDTO;
import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import id.ac.ui.cs.advprog.userfunctionality.model.CartItems;
import id.ac.ui.cs.advprog.userfunctionality.model.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CartCheckoutBuilder {
    private long id;
    private UserEntity user;
    private List<CartItems> items;
    private double totalPrice;
    private String status;

    public CartCheckoutBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public CartCheckoutBuilder setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public CartCheckoutBuilder setItems(List<CartItems> items) {
        this.items = items;
        return this;
    }

    public CartCheckoutBuilder setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public CartCheckoutBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public CartCheckoutBuilder fromDTO(CartCheckoutDTO dto, UserEntity user, List<CartItems> items) {
        this.id = dto.getId();
        this.user = user;
        this.items = items;
        this.totalPrice = dto.getTotalPrice();
        this.status = dto.getStatus();
        return this;
    }

    public CartCheckout build() {
        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setId(this.id);
        cartCheckout.setUser(this.user);
        cartCheckout.setItems(this.items);
        cartCheckout.setTotalPrice(this.totalPrice);
        cartCheckout.setStatus(this.status);
        return cartCheckout;
    }
}