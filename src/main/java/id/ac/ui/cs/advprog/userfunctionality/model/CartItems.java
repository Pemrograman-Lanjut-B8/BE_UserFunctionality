package id.ac.ui.cs.advprog.userfunctionality.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartItems {
    private long bookId;
    private String bookTitle;
    private double price;
    private int quantity;

    public CartItems(long bookId, String bookTitle, double price, int quantity) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.price = price;
        this.quantity = quantity;
    }
}
