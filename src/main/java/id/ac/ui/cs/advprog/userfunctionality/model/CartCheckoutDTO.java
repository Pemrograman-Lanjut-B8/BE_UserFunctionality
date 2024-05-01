package id.ac.ui.cs.advprog.userfunctionality.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter @Setter
public class CartCheckoutDTO {
    private Long cartId;
    private String userId;
    private List<CartItemsDTO> items;
    private double totalPrice;
}
