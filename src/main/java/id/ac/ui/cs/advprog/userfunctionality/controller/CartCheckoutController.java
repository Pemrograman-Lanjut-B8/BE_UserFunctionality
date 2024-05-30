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

    @GetMapping("/checkout/{checkoutId}")
    public ResponseEntity<CartCheckoutDTO> getCartCheckout(@PathVariable Long checkoutId) {
        CartCheckoutDTO checkoutDTO = cartCheckoutService.findCartCheckoutById(checkoutId);
        if (checkoutDTO != null) {
            return ResponseEntity.ok(checkoutDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createCart")
    public ResponseEntity<CartCheckoutDTO> createCartCheckout(@RequestBody CartCheckoutDTO cartCheckout) {
        CartCheckoutDTO createdCartCheckout = cartCheckoutService.createCartCheckout(cartCheckout);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCartCheckout);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CartCheckoutDTO>> cartCheckoutList() {
        List<CartCheckoutDTO> allCartCheckouts = cartCheckoutService.findAll();
        return ResponseEntity.ok(allCartCheckouts);
    }

    @PutMapping("/edit/{checkoutId}")
    public ResponseEntity<CartCheckoutDTO> editCartCheckout(@PathVariable Long checkoutId, @RequestBody CartCheckoutDTO cartCheckout) {
        CartCheckoutDTO updatedCartCheckout = cartCheckoutService.updateCartCheckout(checkoutId, cartCheckout);
        if (updatedCartCheckout != null) {
            return ResponseEntity.ok(updatedCartCheckout);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{checkoutId}")
    public ResponseEntity<Void> deleteCartCheckout(@PathVariable Long checkoutId) {
        boolean deleted = cartCheckoutService.deleteCartCheckout(checkoutId);
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

    @PostMapping("/status/checkout/{checkoutId}")
    public ResponseEntity<Void> checkoutCart(@PathVariable Long checkoutId) {
        boolean statusUpdated = cartCheckoutService.updateCartStatus(checkoutId, "Menunggu Konfirmasi Pembayaran");
        if (statusUpdated) {
            CartCheckoutDTO checkoutDTO = cartCheckoutService.findCartCheckoutById(checkoutId);
            cartCheckoutService.storeCheckedOutBooks(checkoutDTO);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/status/pay/{checkoutId}")
    public ResponseEntity<Void> payCart(@PathVariable Long checkoutId) {
        boolean statusUpdated = cartCheckoutService.updateCartStatus(checkoutId, "Menunggu Pengiriman Buku");
        if (statusUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/status/cancel/{checkoutId}")
    public ResponseEntity<Void> cancelCart(@PathVariable Long checkoutId) {
        boolean statusUpdated = cartCheckoutService.updateCartStatus(checkoutId, "Pembelian Dibatalkan");
        if (statusUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
