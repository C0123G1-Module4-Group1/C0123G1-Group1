package com.example.coffee.order.controller;

import com.example.coffee.order.model.Order;
import com.example.coffee.order.model.OrderDetail;
import com.example.coffee.order.model.SizeProduct;
import com.example.coffee.order.service.ICartService;
import com.example.coffee.order.service.IOderDetailService;
import com.example.coffee.order.service.IOrderService;
import com.example.coffee.order.service.ISizeProductService;
import com.example.coffee.product.model.Product;
import com.example.coffee.product.service.IProductService;
import org.hibernate.engine.jdbc.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
@SessionAttributes("cart")
@RequestMapping("/orderController")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOderDetailService oderDetailService;
    @Autowired
    private IProductService productService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private ISizeProductService sizeProductService;

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
        return "redirect:/orderController/";
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
    @ModelAttribute("cart")
    public Map<Integer,Integer> initCart(){
        return new LinkedHashMap<>();
    }
    @GetMapping("/create")
    public String createOrder(@ModelAttribute("cart") Map<Integer,Integer> list , Model model){
        Order orderDTO = orderService.addOrder();
        List<SizeProduct> sizeProductList = sizeProductService.getAll();
        List<Product> productList = productService.getAll();
        model.addAttribute("productList",productList);
        Map<Product,Integer> mapProduct = cartService.getListProduct(list);
        model.addAttribute("cartDTO",mapProduct);
        double total = cartService.countTotalPayment(list);
        model.addAttribute("total", total);
        model.addAttribute("orderDTO", orderDTO);
        model.addAttribute("sizeProductList", sizeProductList);
        return "/order/createOrder";
    }
    @GetMapping("/{idOrder}/returnOrder")
    public String returnOrder(@ModelAttribute("cart") Map<Integer,Integer> list , Model model,@PathVariable("idOrder") Integer idOrder){
        Order order = orderService.findById(idOrder);
        List<SizeProduct> sizeProductList = sizeProductService.getAll();
        List<Product> productList = productService.getAll();
        model.addAttribute("productList",productList);
        Map<Product,Integer> mapProduct = cartService.getListProduct(list);
        model.addAttribute("cartDTO",mapProduct);
        double total = cartService.countTotalPayment(list);
        model.addAttribute("total", total);
        model.addAttribute("orderDTO", order);
        model.addAttribute("sizeProductList", sizeProductList);
        return "/order/createOrder";
    }
    @GetMapping("/{id}/{idOrder}/addToCart")
    public String addToCart(@PathVariable("id") Integer id,@PathVariable("idOrder") Integer idOrder,
                            RedirectAttributes ra,
                            @ModelAttribute("cart") Map<Integer, Integer> productList) {
//        Product products = productService.findById(id);
//        this.cartService.addProduct(products,productList);
        this.cartService.addProduct(id,productList);
        return "redirect:/orderController/{idOrder}/returnOrder";
    }
    @GetMapping("/{id}/{idOrder}/remove")
    public String removeToCart(@PathVariable("id") Integer id,@PathVariable("idOrder") Integer idOrder,
                               RedirectAttributes ra,
                               @ModelAttribute("cart") Map<Integer, Integer> productList) {
//        Product products = productService.findById(id);
//        this.cartService.removeProduct(products,productList);
        this.cartService.removeProduct(id,productList);
        return "redirect:/orderController/{idOrder}/returnOrder";
    }
    @GetMapping("/{idOrder}/createOrderDetail")
    public String createOrderDetail(@ModelAttribute("cart") Map<Integer, Integer> productList,@PathVariable("idOrder") Integer idOrder){
        this.oderDetailService.addOrderDetail(productList,idOrder);
        return "redirect:/create";
    }
    @GetMapping("/{idOrder}/clearCart")
    public String clearOrder(@ModelAttribute("cart") Map<Integer, Integer> productList,
                             @PathVariable("idOrder") Integer idOrder,
                             RedirectAttributes ra){
        this.cartService.clearList(productList);
        return "redirect:/orderController/{idOrder}/returnOrder";
    }
    @GetMapping("/{idOrder}/removeOrder")
    public String removeOrder(@PathVariable("idOrder") Integer idOrder){
        orderService.deleteOrder(idOrder);
        return "redirect:/orderController/";
    }
//    @GetMapping("/cart")
//    public String carts(@SessionAttribute("cart") Map<Product,Integer> list , Model model){
//        model.addAttribute("cart",list);
//        double total = cartService.countTotalPayment(list);
//        model.addAttribute("total", total);
//        return "/order/createOrder";
//    }
}
