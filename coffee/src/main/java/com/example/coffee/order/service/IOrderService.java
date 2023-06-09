package com.example.coffee.order.service;

import com.example.coffee.order.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IOrderService {
    List<Order> findAll();
    Page<Order> findAll(Integer page);

    boolean deleteOrder(Integer deleteId);

    Order findById(Integer id);

    Order addOrder(String codeOrder, String note, Authentication authentication, Integer coupons);

    Page<Order> findAllByIdContaining(String id, int page);

    String getCodeOrder();
}
