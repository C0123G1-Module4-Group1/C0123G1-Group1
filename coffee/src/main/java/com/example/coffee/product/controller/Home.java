package com.example.coffee.product.controller;

import com.example.coffee.product.model.Email;
import com.example.coffee.product.model.Product;
import com.example.coffee.product.service.impl.EmailService;
import com.example.coffee.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class Home {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private EmailService emailService;
    @GetMapping("/")
    public  String HomeProduct(){
        return "index";
    }
    @GetMapping("/contact")
    public  String Contact(){
        return "contact";
    }
    @GetMapping("/viewProduct")
    public  String ViewProduct(){
        return "view";
    }
    @GetMapping("/shopping")
    public  String shoppingCard(){
        return "shoppingCard";
    }

//    tạo ra sản phẩm ở giao diện người dùng
    @GetMapping("/homeProduct")
    public String ProductPage(Model model) {
        List<Product> productList = iProductService.getAll();
        model.addAttribute("productList", productList);
        model.addAttribute("email",new Email());
        return "product/homeProduct";
    }
//    gởi mail
    @PostMapping("/homeMail")
    public String sendMail(@ModelAttribute("email") Email email) {
        emailService.sendEmail(email);
        return "redirect:/";
    }
//    xem chi tiết 1 sản phẩm  ở giao diện
    @GetMapping("/viewProductType/{id}")
    public  String viewType(@PathVariable("id")Integer id,Model model){
       Product product=iProductService.findById(id);
       model.addAttribute("product",product);
       return "view";
    }

}
