package com.example.coffee.product.controller;

import com.example.coffee.product.model.Email;
import com.example.coffee.product.model.Product;
import com.example.coffee.product.service.IProductService;
import com.example.coffee.product.service.impl.EmailService;
import com.example.coffee.shopping_cart.model.Cart;
import com.example.coffee.shopping_cart.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/home")
@SessionAttributes("cart")
public class Home {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICartService iCartService;
    @Autowired
    private EmailService emailService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("/")
    public String HomeProduct() {
        return "index";
    }

    @GetMapping("/contact")
    public String Contact() {
        return "contact";
    }

    @GetMapping("/viewProduct")
    public String ViewProduct() {
        return "view";
    }

    @GetMapping("/shopping")
    public String shoppingCard() {
        return "shopping-cart";
    }
    @GetMapping("/homeProduct")
    public String creatShoppingCart(@ModelAttribute("cart") Cart cart, Model model) {
        List<Product> productList = iProductService.getAll();
        model.addAttribute("productList", productList);
        Integer countItem = iCartService.countItemQuantity(cart);
        if (countItem == null) {
            countItem = 0;
        }
        model.addAttribute("countItem", countItem);
        model.addAttribute("email", new Email());
        return "product/homeProduct";
    }
//    gởi mail
    @PostMapping("/homeMail")
    public String sendMail(@ModelAttribute("email") Email email) {
        emailService.sendEmail(email);
        return "redirect:/homeClient/";
    }
//    xem chi tiết 1 sản phẩm  ở giao diện
    @GetMapping("/viewProductType/{id}")
    public  String viewType(@PathVariable("id")Integer id,Model model){
       Product product=iProductService.findById(id);
       model.addAttribute("product",product);
       return "view";
    }
    //    tìm kiếm sản phẩm
    @PostMapping("/searchProduct")
    public String search(@RequestParam("name") String name,  Model model) {
        List<Product> productList = iProductService.findAllBySearchProduct(name);
        model.addAttribute("productList", productList);
        model.addAttribute("name",name);
        return "product/homeProduct";
    }


}
