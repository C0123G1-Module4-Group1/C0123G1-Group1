package com.example.coffee.shopping_cart.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, Integer> cart = new LinkedHashMap<>();

    public Cart() {
    }

    public Map<Integer, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Integer, Integer> cart) {
        this.cart = cart;
    }
}
