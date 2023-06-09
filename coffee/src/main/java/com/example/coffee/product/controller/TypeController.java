package com.example.coffee.product.controller;

import com.example.coffee.product.model.Product;
import com.example.coffee.product.model.TypeProduct;
import com.example.coffee.product.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/typeProduct")
public class TypeController {
    @Autowired
    private ITypeService iTypeService;
    @GetMapping("/typeProductCoffee/{id}")
    public String displayTypeCoffee(@PathVariable(value = "id")Integer id, Model model){
        List<Product> productByType=iTypeService.typeProductBy(id);
        model.addAttribute("productByType",productByType);
        return "product/coffee/listTypeProduct";
    }

    @GetMapping("/typeProduct/{id}")
    public String homeTypeCoffee(@PathVariable(value = "id")Integer id, Model model){
        List<Product> homeProductByType=iTypeService.typeProductBy(id);
        model.addAttribute("homeProductByType",homeProductByType);
        return "product/coffee/homeTypeProduct";
    }

}
