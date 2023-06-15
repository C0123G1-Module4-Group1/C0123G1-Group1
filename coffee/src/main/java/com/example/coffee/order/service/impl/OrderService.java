package com.example.coffee.order.service.impl;

import com.example.coffee.coupons.Service.ICouponsService;
import com.example.coffee.coupons.model.Coupons;
import com.example.coffee.coupons.repository.ICouponsRepository;
import com.example.coffee.customer.model.Customer;
import com.example.coffee.customer.service.ICustomerService;
import com.example.coffee.order.model.Order;
import com.example.coffee.order.model.StatusOrder;
import com.example.coffee.order.repository.IOrderRepository;
import com.example.coffee.order.service.IOrderService;

import com.example.coffee.order.service.IStatusOrderService;
import com.example.coffee.staff.model.Staff;
import com.example.coffee.staff.service.IStaffService;
import com.example.coffee.user.model.User;
import com.example.coffee.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.net.Authenticator;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

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
    @Autowired
    private IUserService userService;
    @Autowired
    private ICouponsRepository couponsRepository;

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
        try {
            Order order = orderRepository.findById(id).get();
            order.setDeleteStatus(true);
            orderRepository.save(order);
        } catch (Exception e) {
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
    public Order addOrder(String codeOrder, String note, Authentication authentication, Integer couponsId) {
        User user = userService.findUserByUserName(authentication.getName());
        Staff staff = staffService.findByUser(user);
        Customer customer = customerService.findCustomer(1);
        Coupons coupons = couponsService.findCoupons(couponsId);
        StatusOrder statusOrder = statusOrderService.findById(3);
        Order order = new Order(codeOrder, staff, customer, coupons, statusOrder, note);
        this.orderRepository.save(order);
        return order;
    }

    @Override
    public Page<Order> findAllByIdContaining(String id, int page) {
        return orderRepository.findAllByIdContainingAndDeleteStatusIsFalse(id, PageRequest.of(page, 8, Sort.by("id").descending()));
    }

    @Override
    public String getCodeOrder() {
        String result = "";
        boolean check = true;
        do {
            Integer code = ThreadLocalRandom.current().nextInt(1, 99999);
            if (code < 10000) {
                result = "OD-0" + code;
            } else if (code < 1000) {
                result = "OD-00" + code;
            } else if (code < 100) {
                result = "OD-000" + code;
            } else if (code < 10) {
                result = "OD-0000" + code;
            } else {
                result = "OD-" + code;
            }
            Optional<Order> order = orderRepository.findCouponsByDeleteStatusIsFalseAndCodeOrder(result);
            if (order.isPresent()) {
                check = false;
            }
        } while (!check);
        return result;
    }
}
