package com.example.coffee.order.service;

import com.example.coffee.order.model.OrderDetail;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IOderDetailService {
    Page<OrderDetail> findAllBiIdOrder(Integer id, Integer page);

    Long getTotalOrder(Integer id);

    void addOrderDetail(Map<Integer, Integer> productList, Integer idOrder);
}
