package com.example.coffee.shopping_cart.service.impl;

import com.example.coffee.product.model.Product;
import com.example.coffee.product.service.IProductService;
import com.example.coffee.shopping_cart.model.CartOnline;
import com.example.coffee.shopping_cart.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private IProductService iProductService;

    private boolean checkItemInCart(Integer id, Map<Integer, CartOnline> cart) {
        for (Map.Entry<Integer, CartOnline> entry : cart.entrySet()) {
            if (entry.getKey() == id) {
                return true;
            }
        }
        return false;
    }

    private Map.Entry<Integer, CartOnline> selectItemInCart(Integer id, Map<Integer, CartOnline> cart) {
        for (Map.Entry<Integer, CartOnline> entry : cart.entrySet()) {
            if (entry.getKey() == id) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public void addProduct(Integer id, Map<Integer, CartOnline> cart) {
        Product product = iProductService.findProductById(id);
        if (!checkItemInCart(id, cart)) {
            CartOnline cartOnline = new CartOnline(id, product.getName(), product.getImage(), product.getPrice(), "S", 1f, 1, "");
            cart.put(id, cartOnline);
        } else {
            Map.Entry<Integer, CartOnline> entry = selectItemInCart(id, cart);
            Integer newQuantity = entry.getValue().getQuantity() + 1;
            Float price = entry.getValue().getPrice() + product.getPrice();
            CartOnline cartOnline = new CartOnline(id, entry.getValue().getNameProduct(), entry.getValue().getImg(), price, "S", 1f, newQuantity, "");
            cart.replace(entry.getKey(), cartOnline);
        }
    }

    @Override
    public Integer countProductQuantity(Map<Integer, CartOnline> cart) {
        Integer productQuantity = 0;
        for (Map.Entry<Integer, CartOnline> entry : cart.entrySet()) {
            productQuantity += entry.getValue().getQuantity();
        }
        return productQuantity;
    }


    @Override
    public Integer countItemQuantity(Map<Integer, CartOnline> cart) {
        return cart.size();
    }

    @Override
    public Long countTotalPayment(Map<Integer, CartOnline> cart) {
        long payment = 0;
        for (Map.Entry<Integer, CartOnline> entry : cart.entrySet()) {
            CartOnline cartOnline = entry.getValue();
            payment += cartOnline.getPrice() * cartOnline.getQuantity()*cartOnline.getSizeRate();
        }
        return payment;
    }

    @Override
    public void subProduct(Integer id, Map<Integer, CartOnline> cart) {
        Product product = iProductService.findProductById(id);
        if (checkItemInCart(id, cart)) {
            Map.Entry<Integer, CartOnline> entry = selectItemInCart(id, cart);
            if (entry.getValue().getQuantity() == 0) {
                cart.remove(id);
            }
            Integer newQuantity = entry.getValue().getQuantity() - 1;
            Float price = entry.getValue().getPrice() - product.getPrice();
            CartOnline cartOnline = new CartOnline(id, entry.getValue().getNameProduct(), entry.getValue().getImg(), price, entry.getValue().getSizeName(), entry.getValue().getSizeRate(), newQuantity, "");
            cart.replace(entry.getKey(), cartOnline);
        }
    }

    @Override
    public void addSize(Integer id, Map<Integer, CartOnline> cart) {
        if (checkItemInCart(id, cart)) {
            Map.Entry<Integer, CartOnline> entry = selectItemInCart(id, cart);
            if (entry.getValue().getSizeName().equals("S")) {
                CartOnline cartOnline = new CartOnline(id, entry.getValue().getNameProduct(), entry.getValue().getImg(), 1.2f, "M", entry.getValue().getPrice() * 1.2f, entry.getValue().getQuantity(), "");
                cart.replace(entry.getKey(), cartOnline);

            } else if (entry.getValue().getSizeName().equals("M")) {
                CartOnline cartOnline = new CartOnline(id, entry.getValue().getNameProduct(), entry.getValue().getImg(), 1.5f, "L", entry.getValue().getPrice() * 1.5f, entry.getValue().getQuantity(), "");
                cart.replace(entry.getKey(), cartOnline);
            }
        }
    }

    @Override
    public void subSize(Integer id, Map<Integer, CartOnline> cart) {
        if (checkItemInCart(id, cart)) {
            Map.Entry<Integer, CartOnline> entry = selectItemInCart(id, cart);
            if (entry.getValue().getSizeName().equals("L")) {
                CartOnline cartOnline = new CartOnline(id, entry.getValue().getNameProduct(), entry.getValue().getImg(), 1.2f, "M", entry.getValue().getPrice() * 1.2f, entry.getValue().getQuantity(), "");
                cart.replace(entry.getKey(), cartOnline);

            } else if (entry.getValue().getSizeName().equals("M")) {
                CartOnline cartOnline = new CartOnline(id, entry.getValue().getNameProduct(), entry.getValue().getImg(), 1f, "S", entry.getValue().getPrice() * 1f, entry.getValue().getQuantity(), "");
                cart.replace(entry.getKey(), cartOnline);
            }
        }
    }

    @Override
    public void deleteItem(Integer id, Map<Integer, CartOnline> cart) {
        cart.remove(id);
    }

    @Override
    public List<CartOnline> findAllProductByCart(Map<Integer, CartOnline> cart) {
        List<CartOnline> productList = new ArrayList<>();
        for (Map.Entry<Integer, CartOnline> entry : cart.entrySet()) {
            CartOnline cartOnline = entry.getValue();
            productList.add(cartOnline);
        }
        return productList;
    }
}
