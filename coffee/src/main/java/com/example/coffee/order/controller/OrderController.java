package com.example.coffee.order.controller;

import com.example.coffee.order.model.Order;
import com.example.coffee.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
//@RequestMapping("/orderController")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("/")
    public String getList(@RequestParam(value = "page", defaultValue = "0") Integer page, Model model) {
//        List<Order> orderList = orderService.findAll();
//        model.addAttribute("orderList", orderList);
        Page<Order> orderPage = orderService.findAll(page);
        model.addAttribute("orderPage", orderPage);
        return "/order/listOrder";
//        return "/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        boolean check = orderService.deleteOrder(id);
        redirectAttributes.addFlashAttribute("checkDelete",check);
        return "redirect:/";
    }
}
