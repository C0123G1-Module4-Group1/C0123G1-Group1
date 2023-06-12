package com.example.coffee.order.service;

import com.example.coffee.product.model.Product;


import java.util.Map;

public interface ICartService {
    boolean checkItemInCart(Product products, Map<Integer, Integer> productList);

    Map.Entry<Integer, Integer> productIntegerEntry(Product products, Map<Integer, Integer> productList);

    double countTotalPayment(Map<Integer, Integer> list);

    void addProduct(Integer id, Map<Integer, Integer> productList);

    void removeProduct(Integer id, Map<Integer, Integer> productList);

    void clearList(Map<Integer, Integer> productList);

    Map<Product,Integer> getListProduct(Map<Integer, Integer> productList);
}
