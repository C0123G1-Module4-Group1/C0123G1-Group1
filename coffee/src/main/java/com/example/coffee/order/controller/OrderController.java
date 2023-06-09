package com.example.coffee.order.controller;

import com.example.coffee.order.model.Order;
import com.example.coffee.order.model.OrderDetail;
import com.example.coffee.order.service.IOderDetailService;
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

import javax.servlet.http.HttpServletResponse;


@Controller
//@RequestMapping("/orderController")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOderDetailService oderDetailService;

    @GetMapping("/")
    public String getList(@RequestParam(value = "page", defaultValue = "0") Integer page, Model model, HttpServletResponse httpResponse) {
//        List<Order> orderList = orderService.findAll();
//        model.addAttribute("orderList", orderList);
        Page<Order> orderPage = orderService.findAll(page);
        model.addAttribute("orderPage", orderPage);
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        return "/order/listOrder";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        boolean check = orderService.deleteOrder(id);
        redirectAttributes.addFlashAttribute("checkDelete",check);
        return "redirect:/";
    }
    @GetMapping("/detail/{id}")
    public String detailOrder(@PathVariable Integer id,Model model,@RequestParam(value = "page", defaultValue = "0") Integer page){
        Order order = orderService.findById(id);
        Page<OrderDetail> orderDetailPage = oderDetailService.findAllBiIdOrder(id,page);
        Long totalPrice = oderDetailService.getTotalOrder(id);
        model.addAttribute("orderDetail",order);
        model.addAttribute("orderDetailPage",orderDetailPage);
        model.addAttribute("totalPrice", totalPrice);
        return "/order/orderDetail";
    }
    @GetMapping("/create")
    public String createOrder(){
        this.orderService.addOrder();
        return "/order/createOrder";
    }
}
