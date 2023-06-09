package com.example.coffee.order.service.impl;

import com.example.coffee.order.model.Order;
import com.example.coffee.order.repository.IOrderRepository;
import com.example.coffee.order.service.IOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> findAll(Integer page) {
//        return orderRepository.findAll(PageRequest.of(page, Integer.parseInt("5"), Sort.by("id").descending()));
        return orderRepository.findAllByDeleteStatusIsFalse(PageRequest.of(page, 5, Sort.by("id").descending()));
    }

    @Override
    public boolean deleteOrder(Integer id) {
        try{
            Order order = orderRepository.findById(id).get();
            order.setDeleteStatus(true);
            orderRepository.save(order);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Order findById(Integer id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public void addOrder() {
        Order order = new Order();
        this.orderRepository.save(order);
    }
}
