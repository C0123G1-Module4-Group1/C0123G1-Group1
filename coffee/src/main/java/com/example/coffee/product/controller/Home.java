package com.example.coffee.product.controller;

import com.example.coffee.product.model.Email;
import com.example.coffee.product.model.Product;
import com.example.coffee.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
//@Controller
public class Home {
//    @Autowired
    private IProductService iProductService;
    @GetMapping("/homeProduct")
    public String ProductPage(Model model) {
        List<Product> productList = iProductService.getAll();
        model.addAttribute("productList", productList);
        model.addAttribute("email",new Email());
        return "product/home";
    }
}
