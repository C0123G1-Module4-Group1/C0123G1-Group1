package com.example.coffee.product.controller;

import com.example.coffee.product.dto.ProductDTO;
import com.example.coffee.product.model.Product;
import com.example.coffee.product.service.IProductService;
import com.example.coffee.product.service.ITypeService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/productCoffee")
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ITypeService iTypeService;


    //    trang chủ của admin trước khi vào các chức năng
    @GetMapping("/homeAdmin")
    public String homeAdmin() {
        return "homeAdmin";
    }


    @GetMapping("/listProduct")
    public String ShowListProduct(@RequestParam(value = "page", defaultValue = "0") Integer page, Model model,
                                  HttpServletResponse httpResponse) {
        Page<Product> productPage = iProductService.findAllByStatusIsFalse(page);
        model.addAttribute("productPage", productPage);
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        return "product/listAdminProduct";
    }
    //tạo ms sản phẩm
    @GetMapping("/create")
    private String create(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("typePage", iTypeService.findAll(page));
        return "product/createProduct";
    }

//tạo ms sản phẩm
    @PostMapping("/createProduct")
    public String createProduct(@Validated @ModelAttribute("productDTO") ProductDTO productDTO, BindingResult bindingResult, RedirectAttributes attributes,@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
     new ProductDTO().validate(productDTO,bindingResult);

      if (bindingResult.hasErrors()){
          model.addAttribute("typePage", iTypeService.findAll(page));
          return "product/createProduct";
      }
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        boolean check = iProductService.save(product);
        attributes.addFlashAttribute("mess", check);
        return "redirect:/productCoffee/listProduct";
    }

    //xoá sản phẩm
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        boolean check = iProductService.delete(id);
        redirectAttributes.addFlashAttribute("check", check);
        return "redirect:/productCoffee/listProduct";

    }

    //    chỉnh sửa sản phẩm
    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable(value = "id") Integer id, @RequestParam(value = "page", defaultValue = "0") Integer page, Model model) {
        ProductDTO productDTO = new ProductDTO();
        Product product = iProductService.findProductById(id);
        BeanUtils.copyProperties(product, productDTO);
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("typePage", iTypeService.findAll(page));
        return "product/updateProduct";
    }

    //    chỉnh sửa sản phẩm
    @PostMapping("/editProduct")
    public String editProduct( @Validated @ModelAttribute("productDTO") ProductDTO productDTO,BindingResult bindingResult, RedirectAttributes attributes, @RequestParam(value = "page", defaultValue = "0") Integer page, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("typePage", iTypeService.findAll(page));
            return "product/updateProduct";
        }
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        boolean check = iProductService.save(product);
        attributes.addFlashAttribute("flag", check);
        return "redirect:/productCoffee/listProduct";

    }

    //    hiển thị chi tiết 1 sản phẩm
    @GetMapping("/viewProduct/{id}")
    public String view(@PathVariable("id") Integer id, Model model) {
        ProductDTO productDTO = new ProductDTO();
        Product product = iProductService.findProductById(id);
        BeanUtils.copyProperties(product, productDTO);
        model.addAttribute("productPage", product);
        return "product/vieew";
    }

    //    tìm kiếm sản phẩm
    @PostMapping("/searchProduct")
    public String search(@RequestParam("name") String name, @RequestParam(value = "page", defaultValue = "0") Integer page, Model model,RedirectAttributes attributes) {
        Pageable pageable= PageRequest.of(page,5);
        Page<Product> productPage = iProductService.searchProduct(name,pageable);
        if (productPage.isEmpty()){
            attributes.addFlashAttribute("empty",true);
            return "redirect:/productCoffee/listProduct";
        }
        model.addAttribute("productPage", productPage);
        model.addAttribute("name",name);
        return "product/listAdminProduct";
    }





}

