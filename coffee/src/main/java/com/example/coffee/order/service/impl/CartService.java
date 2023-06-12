package com.example.coffee.order.service.impl;

import com.example.coffee.order.service.ICartService;
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
    @Override
    public boolean checkItemInCart(Product products, Map<Integer, Integer> productIntegerMap) {
        for (Map.Entry<Integer, Integer> e : productIntegerMap.entrySet()) {
            if (e.getKey().equals(products.getId())) {
                return true;
            }
        }
        return false;
    }
    @Override
    public Map.Entry<Integer, Integer> productIntegerEntry(Product products, Map<Integer, Integer> productIntegerMap) {
        for (Map.Entry<Integer, Integer> p : productIntegerMap.entrySet()) {
            if (p.getKey().equals(products.getId())) {
                return p;
            }
        }
        return null;
    }
    @Override
    public double countTotalPayment(Map<Integer, Integer> list) {
        float sum = 0;
        for (Map.Entry<Integer, Integer> e : list.entrySet()) {
            sum = sum + productService.findById(e.getKey()).getPrice() * e.getValue();
        }
        return sum;
    }

    @Override
    public void addProduct(Integer id, Map<Integer, Integer> mapProduct) {
        Integer quantity;
        if (checkItemInCart(productService.findById(id), mapProduct)) {
            Map.Entry<Integer,Integer> m = productIntegerEntry(productService.findById(id),mapProduct);
            quantity = m.getValue() + 1;
            mapProduct.replace(m.getKey(),quantity);
        } else {
            mapProduct.put(id, 1);
        }
    }

    @Override
    public void removeProduct(Integer id, Map<Integer, Integer> productList) {
        Integer quantity;
        if (checkItemInCart(productService.findById(id), productList)) {
            Map.Entry<Integer,Integer> m = productIntegerEntry(productService.findById(id),productList);
            quantity = m.getValue() - 1;
            productList.replace(m.getKey(),quantity);
            if (m.getValue()==0){
                productList.remove(m.getKey());
            }
        } else {
            productList.put(id, 1);
        }
    }

    @Override
    public void clearList(Map<Integer, Integer> productList) {
        productList.clear();
    }

    @Override
    public Map<Product,Integer> getListProduct(Map<Integer, Integer> list) {
        Map<Product,Integer> cart = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> l :list.entrySet()) {
            cart.put(productService.findById(l.getKey()),l.getValue());
        }
        return cart;
    }
}
