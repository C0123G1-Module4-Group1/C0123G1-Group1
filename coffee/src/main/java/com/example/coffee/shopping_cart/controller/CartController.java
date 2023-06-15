package com.example.coffee.shopping_cart.controller;

import com.example.coffee.customer.dto.CustomerDTO;
import com.example.coffee.product.model.Product;
import com.example.coffee.product.service.IProductService;
import com.example.coffee.shopping_cart.model.CartOnline;
import com.example.coffee.shopping_cart.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopping_cart")
@SessionAttributes("cartOnline")
public class CartController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICartService iCartService;

    @GetMapping("/list")
    public String showCart(@SessionAttribute("cartOnline") Map<Integer,CartOnline> cart, Model model) {
        List<CartOnline>productList=iCartService.findAllProductByCart(cart);
        model.addAttribute("productList", productList);
        model.addAttribute("totalPayment", iCartService.countTotalPayment(cart));
        model.addAttribute("customerDTO", new CustomerDTO());
        return "shopping-cart";
    }

    @GetMapping("/operation/{id}")
    public String operationToCart(@PathVariable("id") Integer id, @SessionAttribute("cartOnline") Map<Integer,CartOnline> cart, @RequestParam(value = "action", required = false) String action, RedirectAttributes redirectAttributes) {
        Product product = iProductService.findProductById(id);
        if (product==null) {
            return "/error";
        }
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "increase":
                iCartService.addProduct(product.getId(), cart);
                break;
            case "reduce":
                iCartService.subProduct(product.getId(), cart);
                break;
            case "deleteItem":
                iCartService.deleteItem(product.getId(), cart);
                break;
            case "increaseSize":
                iCartService.addSize(product.getId(), cart);
                break;
            case "reduceSize":
                iCartService.subSize(product.getId(), cart);
                break;
            default:
                iCartService.addProduct(product.getId(), cart);
                redirectAttributes.addFlashAttribute("mess", true);
                return "redirect:/home/homeProduct";
        }
        return "redirect:/shopping_cart/list";
    }

    @GetMapping("clearAllItem")
    public String clearShoppingCart(@SessionAttribute("cartOnline") Map<Integer,CartOnline> cart) {
        cart.clear();
        return "redirect:/shopping_cart/list";
    }
}
