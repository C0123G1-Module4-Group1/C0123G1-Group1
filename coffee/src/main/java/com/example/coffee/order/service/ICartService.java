package com.example.coffee.order.service;

import com.example.coffee.order.dto.CartItem;
import com.example.coffee.product.model.Product;


import java.util.Map;

public interface ICartService {
    void addQuantity(Integer id, Map< Integer, CartItem> productList, Product products);

    void removeQuantity(Integer id, Map<Integer, CartItem> productList, Product products);

    void addSize(Integer id, Map<Integer, CartItem> cart, Product products);

    void removeSize(Integer id, Map<Integer, CartItem> cart, Product products);
//    boolean checkItemInCart(Product products, Map<Integer, Integer> productList);
//
//    Map.Entry<Integer, Integer> productIntegerEntry(Product products, Map<Integer, Integer> productList);
//
    double countTotalPayment(Map<Integer, CartItem> list);
//
//    void addProduct(Integer id, Map<Integer, Integer> productList);
//
//    void removeProduct(Integer id, Map<Integer, Integer> productList);
//
//    void clearList(Map<Integer, CartItem> cart);
//
//    Map<Product,Integer> getListProduct(Map<Integer, Integer> productList);
}
