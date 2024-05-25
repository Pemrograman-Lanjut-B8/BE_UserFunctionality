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
        if (checkoutDTO != null) {
            cartCheckoutService.storeCheckedOutBooks(checkoutDTO);
            return ResponseEntity.ok(checkoutDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createCart")
    public ResponseEntity<CartCheckoutDTO> createCartCheckout(@RequestBody CartCheckoutDTO cartCheckout) {
        CartCheckoutDTO createdCartCheckout = cartCheckoutService.createCartCheckout(cartCheckout);
        cartCheckoutService.storeCheckedOutBooks(createdCartCheckout);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCartCheckout);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CartCheckoutDTO>> cartCheckoutList() {
        List<CartCheckoutDTO> allCartCheckouts = cartCheckoutService.findAll();
        return ResponseEntity.ok(allCartCheckouts);
    }

    @PutMapping("/edit/{cartId}")
    public ResponseEntity<CartCheckoutDTO> editCartCheckout(@PathVariable Long cartId, @RequestBody CartCheckoutDTO cartCheckout) {
        CartCheckoutDTO updatedCartCheckout = cartCheckoutService.updateCartCheckout(cartId, cartCheckout);
        if (updatedCartCheckout != null) {
            return ResponseEntity.ok(updatedCartCheckout);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<Void> deleteCartCheckout(@PathVariable Long cartId) {
        boolean deleted = cartCheckoutService.deleteCartCheckout(cartId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/inventory")
    public ResponseEntity<Void> storeCheckedOutBooks(@RequestBody CartCheckoutDTO cartCheckoutDTO) {
        cartCheckoutService.storeCheckedOutBooks(cartCheckoutDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/status/checkout/{cartId}")
    public ResponseEntity<Void> checkoutCart(@PathVariable Long cartId) {
        boolean statusUpdated = cartCheckoutService.updateCartStatus(cartId, "Menunggu Konfirmasi Pembayaran");
        if (statusUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/status/pay/{cartId}")
    public ResponseEntity<Void> payCart(@PathVariable Long cartId) {
        boolean statusUpdated = cartCheckoutService.updateCartStatus(cartId, "Menunggu Pengiriman Buku");
        if (statusUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/status/cancel/{cartId}")
    public ResponseEntity<Void> cancelCart(@PathVariable Long cartId) {
        boolean statusUpdated = cartCheckoutService.updateCartStatus(cartId, "Pembelian Dibatalkan");
        if (statusUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
