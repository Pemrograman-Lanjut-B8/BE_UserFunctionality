package id.ac.ui.cs.advprog.userfunctionality.controller;

import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
import id.ac.ui.cs.advprog.userfunctionality.service.CartCheckoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartCheckoutController {

    @Autowired
    private CartCheckoutService cartCheckoutService;

    @GetMapping("/create")
    public String createCartCheckoutPage(Model model) {
        CartCheckout cartCheckout = new CartCheckout();
        model.addAttribute("cartCheckout", cartCheckout);
        return "CreateCartCheckout";
    }

    @PostMapping("/create")
    public String createCartCheckoutPost(@ModelAttribute CartCheckout cartCheckout, Model model) {
        cartCheckoutService.create(cartCheckout);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String cartCheckoutListPage(Model model) {
        List<CartCheckout> allCartCheckouts = cartCheckoutService.findAll();
        model.addAttribute("cartCheckouts", allCartCheckouts);
        return "CartCheckoutList";
    }

    @GetMapping("/edit/{cartId}")
    public String editCartCheckoutPage(Model model, @PathVariable("cartId") Long cartId) {
        Optional<CartCheckout> cartCheckout = cartCheckoutService.findById(cartId);
        cartCheckout.ifPresent(value -> model.addAttribute("cartCheckout", value));
        return "EditCartCheckout";
    }

    @PostMapping("/edit/{cartId}")
    public String editCartCheckoutPost(@ModelAttribute CartCheckout cartCheckout, Model model, @PathVariable("cartId") Long cartId) {
        cartCheckoutService.update(cartId, cartCheckout);
        return "redirect:/cart/list";
    }

    @GetMapping("/delete/{cartId}")
    public String deleteCartCheckoutGet(Model model, @PathVariable("cartId") Long cartId) {
        cartCheckoutService.delete(cartId);
        return "redirect:/cart/list";
    }
}
