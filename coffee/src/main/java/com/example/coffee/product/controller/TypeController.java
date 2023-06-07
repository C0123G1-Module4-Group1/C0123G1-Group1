package com.example.coffee.product.controller;

import com.example.coffee.product.model.TypeProduct;
import com.example.coffee.product.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TypeController {
//    @Autowired
//    private ITypeService iTypeService;
//    @GetMapping("/typeProductList")
//    public String displayType(Model model){
//        List<TypeProduct> typeProductList=iTypeService.findAll();
//        model.addAttribute("typeProductList",typeProductList);
//        return "product/create";
//    }
}
