package com.example.coffee.shopping_cart.service.impl;

import com.example.coffee.product.model.Product;
import com.example.coffee.product.service.IProductService;
import com.example.coffee.shopping_cart.model.Cart;
import com.example.coffee.shopping_cart.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private IProductService iProductService;

    private boolean checkItemInCart(Integer id, Cart cart) {
        for (Map.Entry<Integer, Integer> entry : cart.getCart().entrySet()) {
            if (entry.getKey() == id) {
                return true;
            }
        }
        return false;
    }

    private Map.Entry<Integer, Integer> selectItemInCart(Integer id, Cart cart) {
        for (Map.Entry<Integer, Integer> entry : cart.getCart().entrySet()) {
            if (entry.getKey() == id) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public void addProduct(Integer id, Cart cart) {
        if (!checkItemInCart(id, cart)) {
            cart.getCart().put(id, 1);
        } else {
            Map.Entry<Integer, Integer> entry = selectItemInCart(id, cart);
            Integer newQuantity = entry.getValue() + 1;
            cart.getCart().replace(entry.getKey(), newQuantity);
        }
    }

    @Override
    public Integer countProductQuantity(Cart cart) {
        Integer productQuantity = 0;
        for (Map.Entry<Integer, Integer> entry : cart.getCart().entrySet()) {
            productQuantity += entry.getValue();
        }
        return productQuantity;
    }

    @Override
    public Integer countItemQuantity(Cart cart) {
        return cart.getCart().size();
    }

    @Override
    public Long countTotalPayment(Cart cart) {
        long payment = 0;

        for (Map.Entry<Integer, Integer> entry : cart.getCart().entrySet()) {
            Product product =iProductService.findProductById(entry.getKey());
            payment += product.getPrice() * entry.getValue();
        }
        return payment;
    }

    @Override
    public void subProduct(Integer id, Cart cart) {
        Map.Entry<Integer, Integer> entry = selectItemInCart(id, cart);
        Integer newQuantity = entry.getValue() - 1;
        cart.getCart().replace(entry.getKey(), newQuantity);
    }

    @Override
    public void deleteItem(Integer id, Cart cart) {
        Map<Integer, Integer> integerMap = cart.getCart();
        Map.Entry<Integer, Integer> entry = selectItemInCart(id, cart);
        integerMap.remove(entry.getKey());
        cart.setCart(integerMap);
    }
}

