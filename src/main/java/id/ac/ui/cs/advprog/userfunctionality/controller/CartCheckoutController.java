package id.ac.ui.cs.advprog.userfunctionality.controller;

import id.ac.ui.cs.advprog.userfunctionality.dto.CartCheckoutDTO;
import id.ac.ui.cs.advprog.userfunctionality.service.CartCheckoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/cart")
public class CartCheckoutController {

    private final CartCheckoutService cartCheckoutService;

    public CartCheckoutController(CartCheckoutService cartCheckoutService) {
        this.cartCheckoutService = cartCheckoutService;
    }

    @GetMapping("/checkout/{cartId}")
    public CompletableFuture<ResponseEntity<CartCheckoutDTO>> getCartCheckout(@PathVariable Long cartId) {
        return cartCheckoutService.findCartCheckoutById(cartId)
                .thenApply(checkoutDTO ->
                        checkoutDTO != null ? ResponseEntity.ok(checkoutDTO) : ResponseEntity.notFound().build());
    }

    @PostMapping("/createCart")
    public CompletableFuture<ResponseEntity<CartCheckoutDTO>> createCartCheckout(@RequestBody CartCheckoutDTO cartCheckout) {
        return cartCheckoutService.createCartCheckout(cartCheckout)
                .thenApply(createdCartCheckout -> ResponseEntity.status(HttpStatus.CREATED).body(createdCartCheckout));
    }

    @GetMapping("/list")
    public CompletableFuture<ResponseEntity<List<CartCheckoutDTO>>> cartCheckoutList() {
        return cartCheckoutService.findAll()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/edit/{cartId}")
    public CompletableFuture<ResponseEntity<CartCheckoutDTO>> editCartCheckoutPage(@PathVariable Long cartId) {
        return cartCheckoutService.findCartCheckoutById(cartId)
                .thenApply(cartCheckout ->
                        cartCheckout != null ? ResponseEntity.ok(cartCheckout) : ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{cartId}")
    public CompletableFuture<ResponseEntity<CartCheckoutDTO>> editCartCheckout(@PathVariable Long cartId, @RequestBody CartCheckoutDTO cartCheckout) {
        return cartCheckoutService.updateCartCheckout(cartId, cartCheckout)
                .thenApply(updatedCartCheckout ->
                        updatedCartCheckout != null ? ResponseEntity.ok(updatedCartCheckout) : ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{cartId}")
    public CompletableFuture<ResponseEntity<Void>> deleteCartCheckout(@PathVariable Long cartId) {
        return cartCheckoutService.deleteCartCheckout(cartId)
                .thenApply(deleted ->
                        deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build());
    }
}