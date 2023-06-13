package com.example.coffee.order.service.impl;

import com.example.coffee.order.model.OrderDetail;
import com.example.coffee.order.repository.IOrderDetailRepository;
import com.example.coffee.order.service.IOderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Map;

@Service
public class OrderDetailService implements IOderDetailService {
    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    @Override
    public Page<OrderDetail> findAllBiIdOrder(Integer id, Integer page) {
        return orderDetailRepository.findAllBiIdOrder(id, PageRequest.of(page, 5, Sort.by("id").descending()));
    }
public void saveOrderDetail(Integer idOrder, OrderDetail orderDetail){
//        orderDetailRepository.save(orderDetail);
}
    @Override
    public Long getTotalOrder(Integer id) {
//        double total = orderDetailRepository.getTotalOrder(id);
//        double value = orderDetailRepository.
//        if(value != 0){
//            double roundedValue = (double) Math.round(value * 1000) / 1000;
//            return (long) roundedValue;
//        }else {
//            return 0L;
//        }
        return orderDetailRepository.getTotalOrder(id);
//                Math.round((orderDetailRepository.getTotalOrder(id)*100.0)/10.0);
    }

    @Override
    public void addOrderDetail(Map<Integer, Integer> productList, Integer idOrder) {
        for (Map.Entry<Integer, Integer> p : productList.entrySet()) {

        }
    }
}
