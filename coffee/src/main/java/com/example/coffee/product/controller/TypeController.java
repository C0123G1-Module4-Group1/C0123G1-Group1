package com.example.coffee.product.controller;

import com.example.coffee.product.dto.TypeProductDTO;
import com.example.coffee.product.model.Product;
import com.example.coffee.product.model.TypeProduct;
import com.example.coffee.product.service.ITypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/typeProduct")
public class TypeController {
    @Autowired
    private ITypeService iTypeService;

    @GetMapping("/typeProductCoffee/{id}")
    public String displayTypeCoffee(@PathVariable(value = "id") Integer id, Model model) {
        List<Product> productByType = iTypeService.findAllByStatusIsFalse(id);
        model.addAttribute("productByType", productByType);
        return "product/coffee/listTypeProduct1234";
    }

    @GetMapping("/typeProduct/{id}")
    public String homeTypeCoffee(@PathVariable(value = "id") Integer id, Model model) {
        List<Product> homeProductByType = iTypeService.findAllByStatusIsFalse(id);
        model.addAttribute("homeProductByType", homeProductByType);
        return "product/coffee/homeTypeProduct";
    }

    @GetMapping("/typeProductList")
    public String listTypeProduct(@RequestParam(value = "page", defaultValue = "0") Integer page, Model model) {
        Page<TypeProduct> typeProductList = iTypeService.findAll(page);
        model.addAttribute("typeProductList", typeProductList);
        return "product/listTypeProduct";
    }

    @GetMapping("/delete/{id}")
    public String deleteTypeProduct(@PathVariable("id") Integer id,RedirectAttributes attributes) {
        boolean check = iTypeService.delete(id);
        attributes.addFlashAttribute("check",check);
        return "redirect:/typeProduct/typeProductList";
    }

    @GetMapping("/createType")
    public String createType(Model model) {
        model.addAttribute("createType", new TypeProductDTO());
        return "product/createTypeProduct";
    }

    @PostMapping("/createTypeProduct")
    public String createTypeProduct(@Validated  @ModelAttribute("createType") TypeProductDTO typeProductDTO, BindingResult  bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()){
            return "product/createTypeProduct";
        }

        TypeProduct typeProduct = new TypeProduct();
        BeanUtils.copyProperties(typeProductDTO, typeProduct);
        boolean check = iTypeService.save(typeProduct);
        attributes.addFlashAttribute("mess",check);
        return "redirect:/typeProduct/typeProductList";
    }
    @GetMapping("/updateType/{id}")
    public String updateType(@PathVariable("id")Integer id, Model model){
        TypeProductDTO typeProductDTO=new TypeProductDTO();
       TypeProduct typeProduct= iTypeService.findById(id);
        BeanUtils.copyProperties(typeProduct,typeProductDTO);
        model.addAttribute("updateType",typeProductDTO);
        return "product/updateTypeProduct";
    }
    @PostMapping ("/editType")
    public String editTypeProduct(@Validated @ModelAttribute("updateType")TypeProductDTO typeProductDTO,BindingResult bindingResult,RedirectAttributes attributes){
      if (bindingResult.hasErrors()){
          return "product/updateTypeProduct";
      }
       TypeProduct typeProduct=new TypeProduct();
       BeanUtils.copyProperties(typeProductDTO,typeProduct);
        boolean check= iTypeService.save(typeProduct);
       attributes.addFlashAttribute("flag",check);
       return "redirect:/typeProduct/typeProductList";
    }
    @PostMapping("/searchTypeProduct")
    public String search(@RequestParam("name") String name, Model model) {
        List<TypeProduct> typeProductList=iTypeService.findAllByTypeProduct(name);
        if (typeProductList.isEmpty()){
            model.addAttribute("searchMess","There is no data for search");
        }
        model.addAttribute("typeProductList", typeProductList);
        model.addAttribute("name",name);
        return "product/listTypeProduct";
    }


}
