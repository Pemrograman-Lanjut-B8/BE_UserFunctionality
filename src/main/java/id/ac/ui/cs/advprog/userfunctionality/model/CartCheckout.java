package id.ac.ui.cs.advprog.userfunctionality.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Entity
@Data
@Table(name = "cart_checkout")
public class CartCheckout {

    @Id
    @Column
    private long id;

    @ManyToOne
    private UserEntity user;

    @OneToMany(fetch = FetchType.EAGER)
    private List<CartItems> items;

    @Column
    private double totalPrice;

    @Column
    private String status;

    public CartCheckout() {
        this.items = new ArrayList<>();
    }
}
