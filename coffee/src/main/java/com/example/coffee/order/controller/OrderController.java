package com.example.coffee.order.controller;

import com.example.coffee.order.dto.CartItem;
import com.example.coffee.order.model.Order;
import com.example.coffee.order.model.OrderDetail;
import com.example.coffee.order.model.SizeProduct;
import com.example.coffee.order.service.*;
import com.example.coffee.product.model.Product;
import com.example.coffee.product.service.IProductService;
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
        Page<Order> orderPage = orderService.findAll(page);
        model.addAttribute("orderPage", orderPage);
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        return "/order/listOrder";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        boolean check = orderService.deleteOrder(id);
        redirectAttributes.addFlashAttribute("checkDelete", check);
        return "redirect:/orderController/";
    }

    @GetMapping("/detail/{id}")
    public String detailOrder(@PathVariable Integer id, Model model, @RequestParam(value = "page", defaultValue = "0") Integer page) {
        Order order = orderService.findById(id);
        Page<OrderDetail> orderDetailPage = oderDetailService.findAllBiIdOrder(id, page);
        Long totalPrice = oderDetailService.getTotalOrder(id);
        model.addAttribute("orderDetail", order);
        model.addAttribute("orderDetailPage", orderDetailPage);
        model.addAttribute("totalPrice", totalPrice);
        return "/order/orderDetail";
    }

    //    @ModelAttribute("cart")
//    public Map<Integer, Integer> initCart() {
//        return new LinkedHashMap<>();
//    }
    @ModelAttribute("cart")
    public Map<Integer, CartItem> initCart() {
        return new LinkedHashMap<>();
    }

    @GetMapping("/create")
    public String createOrder(@ModelAttribute("cart") Map<Integer, CartItem> cartDTO, Model model) {
        cartDTO.clear();
//        Order orderDTO = orderService.addOrder();
//        Integer idOrder = orderDTO.getId();
//        List<SizeProduct> sizeProductList = sizeProductService.getAll();
        List<Product> productList = productService.getAll();
//        Map<Product, Integer> mapProduct = cartService.getListProduct(list);
//        Map<CartItem, String> mapOrderDetailDTO = orderDetailDTOService.getListOrderDetailDTO(mapProduct, idOrder, map);
//        total chua co * size
//        double total = cartService.countTotalPayment(list);
        model.addAttribute("productList", productList);
        model.addAttribute("cartDTO", cartDTO);
//        model.addAttribute("cartDTO", mapProduct);
//        model.addAttribute("total", total);
//        model.addAttribute("orderDTO", orderDTO);
//        model.addAttribute("sizeProductList", sizeProductList);
//        model.addAttribute("mapOrderDetailDTO", mapOrderDetailDTO);
        return "/order/createOrder";
    }

    @GetMapping("/returnCreateOrder")
    public String returnOrder(@ModelAttribute("cart") Map<Integer, CartItem> cartDTO, Model model) {
//        Order order = orderService.findById(idOrder);
//        List<SizeProduct> sizeProductList = sizeProductService.getAll();
        List<Product> productList = productService.getAll();
        model.addAttribute("productList", productList);
//        Map<Product, Integer> mapProduct = cartService.getListProduct(list);
//        Map<OrderDetailDTO, String> mapOrderDetailDTO = orderDetailDTOService.getListOrderDetailDTO(mapProduct, idOrder, map);
        model.addAttribute("cartDTO", cartDTO);
        double total = cartService.countTotalPayment(cartDTO);
        model.addAttribute("total", total);
//        model.addAttribute("orderDTO", order);
//        model.addAttribute("sizeProductList", sizeProductList);
//        model.addAttribute("mapOrderDetailDTO", mapOrderDetailDTO);
        return "/order/createOrder";
    }

    @GetMapping("/{id}/addQuantity")
    public String addQuantity(@PathVariable("id") Integer id,
                            RedirectAttributes ra,
                            @ModelAttribute("cart") Map<Integer, CartItem> cart) {
        Product products = productService.findById(id);
//        this.cartService.addProduct(products,productList);
        this.cartService.addQuantity(id, cart, products);
        return "redirect:/orderController/returnCreateOrder";
    }
    @GetMapping("/{id}/removeQuantity")
    public String removeQuantity(@PathVariable("id") Integer id,
                            RedirectAttributes ra,
                            @ModelAttribute("cart") Map<Integer, CartItem> cart) {
        Product products = productService.findById(id);
//        this.cartService.addProduct(products,productList);
        this.cartService.removeQuantity(id, cart, products);
        return "redirect:/orderController/returnCreateOrder";
    }
    @GetMapping("/{id}/addSize")
    public String addSize(@PathVariable("id") Integer id,
                            RedirectAttributes ra,
                            @ModelAttribute("cart") Map<Integer, CartItem> cart) {
        Product products = productService.findById(id);
//        this.cartService.addProduct(products,productList);
        this.cartService.addSize(id, cart, products);
        return "redirect:/orderController/returnCreateOrder";
    }
    @GetMapping("/{id}/removeSize")
    public String removeSize(@PathVariable("id") Integer id,
                                 RedirectAttributes ra,
                                 @ModelAttribute("cart") Map<Integer, CartItem> cart) {
        Product products = productService.findById(id);
//        this.cartService.addProduct(products,productList);
        this.cartService.removeSize(id, cart, products);
        return "redirect:/orderController/returnCreateOrder";
    }
//    @GetMapping("/{id}/{idOrder}/remove")
//    public String removeToCart(@PathVariable("id") Integer id, @PathVariable("idOrder") Integer idOrder,
//                               RedirectAttributes ra,
//                               @ModelAttribute("cart") Map<Integer, Integer> productList) {
//        this.cartService.removeProduct(id, productList);
//        return "redirect:/orderController/{idOrder}/returnOrder";
//    }

//    @GetMapping("/addOrderDetail")
//    public String addOrderDetailToCart(@RequestParam Integer idProduct, @RequestParam Integer idSize) {
//        Product product = productService.findById(idProduct);
////        orderDetailDTOService.addOrderDetailDTO(product.getId(),product.)
//        return "/listBlog";
//    }

    @GetMapping("/createOrderDetail")
    public String createOrderDetail(@ModelAttribute("cart") Map<Integer, CartItem> cart) {
        Order orderDTO = orderService.addOrder();
        Integer idOrder = orderDTO.getId();
        this.oderDetailService.addOrderDetail(cart,idOrder);
        return "redirect:/orderController/create";
    }

    @GetMapping("/clearCart")
    public String clearOrder(@ModelAttribute("cart") Map<Integer, CartItem> cart,
                             RedirectAttributes ra) {
        cart.clear();
        return "redirect:/orderController/returnCreateOrder";
    }

    @GetMapping("/removeOrder")
    public String removeOrder(@ModelAttribute("cart") Map<Integer, CartItem> cart) {
        cart.clear();
        return "redirect:/orderController/";
    }
}
