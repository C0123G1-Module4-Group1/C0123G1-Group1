package com.example.coffee.order.service.impl;

import com.example.coffee.coupons.Service.ICouponsService;
import com.example.coffee.coupons.model.Coupons;
import com.example.coffee.customer.model.Customer;
import com.example.coffee.customer.service.ICustomerService;
import com.example.coffee.order.model.Order;
import com.example.coffee.order.model.StatusOrder;
import com.example.coffee.order.repository.IOrderRepository;
import com.example.coffee.order.service.IOrderService;

import com.example.coffee.order.service.IStatusOrderService;
import com.example.coffee.staff.model.Staff;
import com.example.coffee.staff.service.IStaffService;
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
    @Autowired
    private IStaffService staffService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICouponsService couponsService;
    @Autowired
    private IStatusOrderService statusOrderService;
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> findAll(Integer page) {
//        return orderRepository.findAll(PageRequest.of(page, Integer.parseInt("5"), Sort.by("id").descending()));
        return orderRepository.findAllByDeleteStatusIsFalse(PageRequest.of(page, 8, Sort.by("id").descending()));
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
    public Order addOrder() {
        Staff staff = staffService.findById(1);
        Customer customer = customerService.findCustomer(1);
        Coupons coupons = couponsService.findCoupons(1);
        StatusOrder statusOrder = statusOrderService.findById(3);
        Order order = new Order(staff,customer,coupons,statusOrder);
        this.orderRepository.save(order);
        return order;
    }
}
