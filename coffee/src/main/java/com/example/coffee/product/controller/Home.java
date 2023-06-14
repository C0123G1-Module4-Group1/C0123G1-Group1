package com.example.coffee.product.controller;

import com.example.coffee.product.model.Email;
import com.example.coffee.product.model.Product;
import com.example.coffee.product.service.IProductService;
import com.example.coffee.product.service.impl.EmailService;

import com.example.coffee.shopping_cart.model.CartOnline;
import com.example.coffee.shopping_cart.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/home")
@SessionAttributes("cartOnline")
public class Home {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICartService iCartService;
    @Autowired
    private EmailService emailService;

    @ModelAttribute("cartOnline")
    public Map<Integer, CartOnline>  setupCart() {
        return new  LinkedHashMap<>();
    }

    @GetMapping("/")
    public String HomeProduct(Model model) {
        List<Product> productList = iProductService.getAll();
        model.addAttribute("product",productList);
        return "index";
    }

    @GetMapping("/contact")
    public String Contact(Model model) {
        model.addAttribute("email",new Email());
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
    public String creatShoppingCart(@ModelAttribute("cartOnline") Map<Integer,CartOnline> cart, Model model) {
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
    public String sendMail(@ModelAttribute("email") Email email, RedirectAttributes redirectAttributes) {
       boolean check= emailService.sendEmail(email);
        redirectAttributes.addFlashAttribute("message",check);
        return "redirect:/home/homeProduct";
    }
//    xem chi tiết 1 sản phẩm  ở giao diện
    @GetMapping("/viewProductType/{id}")
    public  String viewType(@PathVariable("id")Integer id,Model model){
       Product product=iProductService.findProductById(id);
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
