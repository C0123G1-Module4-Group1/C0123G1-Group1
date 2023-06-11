package com.example.coffee.shopping_cart.controller;

import com.example.coffee.product.model.Product;
import com.example.coffee.product.service.IProductService;
import com.example.coffee.shopping_cart.model.Cart;
import com.example.coffee.shopping_cart.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("cart")
public class ShoppingCartController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICartService iCartService;

    @ModelAttribute("shoppingCart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("/list")
    public String creatShoppingCart(@ModelAttribute("cart")Cart cart, Model model) {
        Page<Product> productList = iProductService.findAll(0);
        model.addAttribute("productList",productList);
        Integer countItem=iCartService.countItemQuantity(cart);
            if (countItem==null){
            countItem=0;
        }
        model.addAttribute("countItem",countItem);
        return"index";
    }
}
