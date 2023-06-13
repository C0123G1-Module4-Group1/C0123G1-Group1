package com.example.coffee.order.service.impl;

import com.example.coffee.order.dto.CartItem;
import com.example.coffee.order.model.SizeProduct;
import com.example.coffee.order.service.ICartItemService;
import com.example.coffee.order.service.ICartService;
import com.example.coffee.order.service.ISizeProductService;
import com.example.coffee.product.model.Product;
import com.example.coffee.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService implements ICartService {
    @Autowired
    private IProductService productService;
    @Autowired
    private ISizeProductService sizeProductService;

    //    @Override
    public boolean checkItemInCart(Integer id, Map<Integer, CartItem> cart) {
        for (Map.Entry<Integer, CartItem> e : cart.entrySet()) {
            if (e.getKey().equals(id)) {
                return true;
            }
        }
        return false;
    }

    //    @Override
    public Map.Entry<Integer, CartItem> productIntegerEntry(Integer id, Map<Integer, CartItem> productIntegerMap) {
        for (Map.Entry<Integer, CartItem> p : productIntegerMap.entrySet()) {
            if (p.getKey().equals(id)) {
                return p;
            }
        }
        return null;
    }

    //    @Override
    public double countTotalPayment(Map<Integer, CartItem> cart) {
        float sum = 0;
        for (Map.Entry<Integer, CartItem> e : cart.entrySet()) {
            sum = sum + e.getValue().getPriceProduct() * e.getValue().getQuantity() * e.getValue().getSizeRate();
        }
        return sum;
    }

    //
    @Override
    public void addQuantity(Integer id, Map<Integer, CartItem> cart, Product product) {
        Integer quantity;
        if (checkItemInCart(id, cart)) {
            Map.Entry<Integer, CartItem> m = productIntegerEntry(id, cart);
            quantity = m.getValue().getQuantity() + 1;
            CartItem cartItem = new CartItem(m.getValue().getId(), m.getValue().getIdProduct(), m.getValue().getNameProduct(), m.getValue().getSizeProduct(), m.getValue().getSizeRate(), m.getValue().getPriceProduct(), quantity);
            cart.replace(m.getKey(), cartItem);
        } else {
            CartItem cartItem = new CartItem(product.getId(), product.getId(), product.getName(), "S", 1f, product.getPrice(), 1);
            cart.put(id, cartItem);
        }
    }

    @Override
    public void removeQuantity(Integer id, Map<Integer, CartItem> cart, Product product) {
        Integer quantity;
        if (checkItemInCart(id, cart)) {
            Map.Entry<Integer, CartItem> m = productIntegerEntry(id, cart);
            quantity = m.getValue().getQuantity() - 1;
            CartItem cartItem = new CartItem(m.getValue().getId(), m.getValue().getIdProduct(), m.getValue().getNameProduct(), m.getValue().getSizeProduct(), m.getValue().getSizeRate(), m.getValue().getPriceProduct(), quantity);
            cart.replace(m.getKey(), cartItem);
            if (m.getValue().getQuantity() == 0) {
                cart.remove(m.getKey());
            }
        }
    }

    @Override
    public void addSize(Integer id, Map<Integer, CartItem> cart, Product products) {
        if (checkItemInCart(id, cart)) {
            Map.Entry<Integer, CartItem> m = productIntegerEntry(id, cart);
//            SizeProduct sizeProduct = sizeProductService.findBySize(m.getValue().getSizeProduct());
            if(m.getValue().getSizeProduct().equals("S")){
                CartItem cartItem = new CartItem(m.getValue().getId(), m.getValue().getIdProduct(), m.getValue().getNameProduct(), "M", 1.2f, m.getValue().getPriceProduct(), m.getValue().getQuantity());
                cart.replace(m.getKey(), cartItem);

            }else if (m.getValue().getSizeProduct().equals("M")){
                CartItem cartItem = new CartItem(m.getValue().getId(), m.getValue().getIdProduct(), m.getValue().getNameProduct(), "L", 1.5f, m.getValue().getPriceProduct(), m.getValue().getQuantity());
                cart.replace(m.getKey(), cartItem);
            }
        }
    }

    @Override
    public void removeSize(Integer id, Map<Integer, CartItem> cart, Product products) {
        if (checkItemInCart(id, cart)) {
            Map.Entry<Integer, CartItem> m = productIntegerEntry(id, cart);
//            SizeProduct sizeProduct = sizeProductService.findBySize(m.getValue().getSizeProduct());
            if(m.getValue().getSizeProduct().equals("L")){
                CartItem cartItem = new CartItem(m.getValue().getId(), m.getValue().getIdProduct(), m.getValue().getNameProduct(), "M", 1.2f, m.getValue().getPriceProduct(), m.getValue().getQuantity());
                cart.replace(m.getKey(), cartItem);

            }else if (m.getValue().getSizeProduct().equals("M")){
                CartItem cartItem = new CartItem(m.getValue().getId(), m.getValue().getIdProduct(), m.getValue().getNameProduct(), "S", 1f, m.getValue().getPriceProduct(), m.getValue().getQuantity());
                cart.replace(m.getKey(), cartItem);
            }
        }
    }

//
//    @Override
//    public void removeProduct(Integer id, Map<Integer, Integer> productList) {
//        Integer quantity;
//        if (checkItemInCart(productService.findById(id), productList)) {
//            Map.Entry<Integer,Integer> m = productIntegerEntry(productService.findById(id),productList);
//            quantity = m.getValue() - 1;
//            productList.replace(m.getKey(),quantity);
//            if (m.getValue()==0){
//                productList.remove(m.getKey());
//            }
//        } else {
//            productList.put(id, 1);
//        }
//    }
//
//    @Override
//    public void clearList(Map<Integer, CartItem> cart) {
//        cart.clear();
//    }
//
//    @Override
//    public Map<Product,Integer> getListProduct(Map<Integer, Integer> list) {
//        Map<Product,Integer> cart = new LinkedHashMap<>();
//        for (Map.Entry<Integer, Integer> l :list.entrySet()) {
//            cart.put(productService.findById(l.getKey()),l.getValue());
//        }
//        return cart;
//    }
}
