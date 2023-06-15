package com.example.coffee.shopping_cart.service;

import com.example.coffee.product.model.Product;

import com.example.coffee.shopping_cart.model.CartOnline;

import java.util.List;
import java.util.Map;

public interface ICartService {
    void addProduct(Integer id, Map<Integer,CartOnline> cart);

    Integer countProductQuantity(Map<Integer,CartOnline> cart);

    Integer countItemQuantity(Map<Integer,CartOnline> cart);

    Long countTotalPayment(Map<Integer,CartOnline> cart);

    void subProduct(Integer id,Map<Integer,CartOnline> cart);

    void addSize(Integer id,Map<Integer,CartOnline> cart);

    void subSize(Integer id,Map<Integer,CartOnline> cart);
    void deleteItem(Integer id, Map<Integer,CartOnline> cart);

    List<CartOnline> findAllProductByCart(Map<Integer,CartOnline> cart);

}
