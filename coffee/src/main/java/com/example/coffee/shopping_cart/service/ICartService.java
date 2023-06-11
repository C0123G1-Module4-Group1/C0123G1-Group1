package com.example.coffee.shopping_cart.service;

import com.example.coffee.shopping_cart.model.Cart;

public interface ICartService {
    void addProduct(Integer id, Cart cart);

    Integer countProductQuantity(Cart cart);

    Integer countItemQuantity(Cart cart);

    Long countTotalPayment(Cart cart);

    void subProduct(Integer id, Cart cart);

    void deleteItem(Integer id, Cart cart);
}
