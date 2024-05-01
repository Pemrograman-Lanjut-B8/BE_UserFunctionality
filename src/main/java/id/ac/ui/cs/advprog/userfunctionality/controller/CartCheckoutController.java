package id.ac.ui.cs.advprog.userfunctionality.controller;

import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckoutDTO;
import id.ac.ui.cs.advprog.userfunctionality.service.CartCheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartCheckoutController {

    private final CartCheckoutService cartCheckoutService;

    @Autowired
    public CartCheckoutController(CartCheckoutService cartCheckoutService) {
        this.cartCheckoutService = cartCheckoutService;
    }

    @GetMapping("/checkout/{cartId}")
    public ResponseEntity<CartCheckoutDTO> getCartCheckout(@PathVariable Long cartId) {
        CartCheckoutDTO checkoutDTO = cartCheckoutService.findCartCheckoutById(cartId);
        return ResponseEntity.ok(checkoutDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<CartCheckoutDTO> createCartCheckout(@RequestBody CartCheckoutDTO cartCheckout) {
        CartCheckoutDTO createdCartCheckout = cartCheckoutService.createCartCheckout(cartCheckout);
        return ResponseEntity.ok(createdCartCheckout);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CartCheckoutDTO>> cartCheckoutList() {
        List<CartCheckoutDTO> allCartCheckouts = cartCheckoutService.findAll();
        return ResponseEntity.ok(allCartCheckouts);
    }

    @GetMapping("/edit/{cartId}")
    public ResponseEntity<CartCheckoutDTO> editCartCheckoutPage(@PathVariable("cartId") Long cartId) {
        CartCheckoutDTO cartCheckout = cartCheckoutService.findCartCheckoutById(cartId);
        return ResponseEntity.ok(cartCheckout);
    }

    @PutMapping("/edit/{cartId}")
    public ResponseEntity<CartCheckoutDTO> editCartCheckout(@PathVariable("cartId") Long cartId, @RequestBody CartCheckoutDTO cartCheckout) {
        CartCheckoutDTO updatedCartCheckout = cartCheckoutService.updateCartCheckout(cartId, cartCheckout);
        return ResponseEntity.ok(updatedCartCheckout);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<Void> deleteCartCheckout(@PathVariable("cartId") Long cartId) {
        boolean deleted = cartCheckoutService.deleteCartCheckout(cartId);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
