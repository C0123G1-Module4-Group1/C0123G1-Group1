package com.example.coffee.product.controller;

import com.example.coffee.product.model.SizeProduct;
import com.example.coffee.product.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SizeController {
//    @Autowired
//    private ISizeService iSizeService;
//    @GetMapping("/sizeProductList")
//    public String displaySize(Model model){
//        List<SizeProduct>sizeProductList=iSizeService.findAll();
//        model.addAttribute("sizeProductList",sizeProductList);
//        return "/product/create";
//    }
}
