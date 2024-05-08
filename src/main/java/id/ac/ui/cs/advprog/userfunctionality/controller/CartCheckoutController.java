package id.ac.ui.cs.advprog.userfunctionality.controller;

import id.ac.ui.cs.advprog.userfunctionality.dto.CartCheckoutDTO;
import id.ac.ui.cs.advprog.userfunctionality.service.CartCheckoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartCheckoutController {

    private final CartCheckoutService cartCheckoutService;

    public CartCheckoutController(CartCheckoutService cartCheckoutService) {
        this.cartCheckoutService = cartCheckoutService;
    }

    @GetMapping("/checkout/{cartId}")
    public ResponseEntity<CartCheckoutDTO> getCartCheckout(@PathVariable Long cartId) {
        CartCheckoutDTO checkoutDTO = cartCheckoutService.findCartCheckoutById(cartId);
        if (checkoutDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(checkoutDTO);
    }

    @PostMapping("/createCart")
    public ResponseEntity<CartCheckoutDTO> createCartCheckout(@RequestBody CartCheckoutDTO cartCheckout) {
        CartCheckoutDTO createdCartCheckout = cartCheckoutService.createCartCheckout(cartCheckout);
        return new ResponseEntity<>(createdCartCheckout, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CartCheckoutDTO>> cartCheckoutList() {
        List<CartCheckoutDTO> allCartCheckouts = cartCheckoutService.findAll();
        return ResponseEntity.ok(allCartCheckouts);
    }

    @GetMapping("/edit/{cartId}")
    public ResponseEntity<CartCheckoutDTO> editCartCheckoutPage(@PathVariable Long cartId) {
        CartCheckoutDTO cartCheckout = cartCheckoutService.findCartCheckoutById(cartId);
        if (cartCheckout == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartCheckout);
    }

    @PutMapping("/edit/{cartId}")
    public ResponseEntity<CartCheckoutDTO> editCartCheckout(@PathVariable Long cartId, @RequestBody CartCheckoutDTO cartCheckout) {
        CartCheckoutDTO updatedCartCheckout = cartCheckoutService.updateCartCheckout(cartId, cartCheckout);
        if (updatedCartCheckout == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCartCheckout);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<Void> deleteCartCheckout(@PathVariable Long cartId) {
        boolean deleted = cartCheckoutService.deleteCartCheckout(cartId);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}