package com.example.coffee.shopping_cart.controller;

import com.example.coffee.product.model.Product;
import com.example.coffee.product.service.IProductService;
import com.example.coffee.shopping_cart.model.Cart;
import com.example.coffee.shopping_cart.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/shopping_cart")
@SessionAttributes("cart")
public class CartController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICartService iCartService;

    @GetMapping("/list")
    public String showCart(@SessionAttribute("cart") Cart cart, Model model) {
        List<Product>productList=iCartService.findAllProductByCart(cart);
        model.addAttribute("productList", productList);
        model.addAttribute("countQuantity", iCartService.countItemQuantity(cart));
        model.addAttribute("countProduct", iCartService.countProductQuantity(cart));
        model.addAttribute("totalPayment", iCartService.countTotalPayment(cart));
        return "shopping-cart";
    }

    @GetMapping("/operation/{id}")
    public String operationToCart(@PathVariable("id") Integer id, @SessionAttribute("cart") Cart cart, @RequestParam(value = "action", required = false) String action) {
        Product product = iProductService.findProductById(id);
        if (product==null) {
            return "/error";
        }
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "increase":
                iCartService.addProduct(product.getId(), cart);
                break;
            case "reduce":
                iCartService.subProduct(product.getId(), cart);
                break;
            case "deleteItem":
                iCartService.deleteItem(product.getId(), cart);
                break;
            default:
                iCartService.addProduct(product.getId(), cart);
                return "redirect:/home/homeProduct";
        }
        return "redirect:/shopping_cart/list";
    }

    @GetMapping("clearAllItem")
    public String clearShoppingCart(@SessionAttribute("cart") Cart cart) {
        cart.getCart().clear();
        return "redirect:/shopping_cart/list";
    }
}
