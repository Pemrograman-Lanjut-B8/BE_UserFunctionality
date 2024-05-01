package id.ac.ui.cs.advprog.userfunctionality.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartItemsDTO {
    private Long bookId;
    private String bookTitle;
    private double price;
    private int quantity;

    public CartItemsDTO(long bookId, String bookTitle, double price, int quantity) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.price = price;
        this.quantity = quantity;
    }
}
