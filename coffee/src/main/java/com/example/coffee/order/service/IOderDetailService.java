package com.example.coffee.order.service;

import com.example.coffee.order.dto.CartItem;
import com.example.coffee.order.model.OrderDetail;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IOderDetailService {
    Page<OrderDetail> findAllBiIdOrder(Integer id, Integer page);

    Long getTotalOrder(Integer id);

    boolean addOrderDetail(Map<Integer, CartItem> cart, Integer idOrder);
}
