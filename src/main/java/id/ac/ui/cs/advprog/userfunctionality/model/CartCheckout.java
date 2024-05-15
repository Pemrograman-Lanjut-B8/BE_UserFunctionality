package id.ac.ui.cs.advprog.userfunctionality.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

@Getter @Setter
public class CartCheckout {
    private Long cartId;
    private String userId;
    private List<CartItems> items;
    private double totalPrice;

    public CartCheckout() {
        this.userId = UUID.randomUUID().toString();
        this.items = new ArrayList<>();
    }
}
