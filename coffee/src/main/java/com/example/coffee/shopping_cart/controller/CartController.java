package com.example.coffee.shopping_cart.controller;

import com.example.coffee.product.model.Product;
import com.example.coffee.product.service.IProductService;
import com.example.coffee.shopping_cart.model.Cart;
import com.example.coffee.shopping_cart.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@SessionAttributes("cart")
@RequestMapping("/shopping-cart ")
public class CartController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICartService iCartService;

    @GetMapping("/list")
    public String showCart(@SessionAttribute("cart") Cart cart, Model model) {
        model.addAttribute("cart", cart);
        model.addAttribute("countQuantity", iCartService.countItemQuantity(cart));
        model.addAttribute("countProduct", iCartService.countProductQuantity(cart));
        model.addAttribute("totalPayment", iCartService.countTotalPayment(cart));
        return "shopping-cart";
    }

    @GetMapping("/operation/{id}")
    public String operationToCart(@PathVariable("id") Integer id, @SessionAttribute("cart") Cart cart, @RequestParam(value = "action", required = false) String action) {
        Optional<Product> product = iProductService.findProduct(id);
        if (!product.isPresent()) {
            return "/error";
        }
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "increase":
                iCartService.addProduct(product.get().getId(), cart);
                break;
            case "reduce":
                iCartService.subProduct(product.get().getId(), cart);
                break;
            case "deleteItem":
                iCartService.deleteItem(product.get().getId(), cart);
                break;
            default:
                iCartService.addProduct(product.get().getId(), cart);
                return "redirect:/";
        }
        return "redirect:/shopping-cart";
    }

    @GetMapping("clearAllItem")
    public String clearShoppingCart(@SessionAttribute("cart") Cart cart) {
        cart.getCart().clear();
        return "redirect:/shopping-cart";
    }
}
