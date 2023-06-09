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

@Service
public class OrderDetailService implements IOderDetailService {
    @Autowired
    private IOrderDetailRepository orderDetailRepository;
    @Override
    public Page<OrderDetail> findAllBiIdOrder(Integer id, Integer page) {
        return orderDetailRepository.findAllBiIdOrder(id, PageRequest.of(page, 5, Sort.by("id").descending()));
    }

    @Override
    public Long  getTotalOrder(Integer id) {
//        double value = orderDetailRepository.getTotalOrder(id);
//        if(value != 0){
//            double roundedValue = (double) Math.round(value * 1000) / 1000;
//            return (long) roundedValue;
//        }else {
//            return 0L;
//        }
return orderDetailRepository.getTotalOrder(id);
//                Math.round((orderDetailRepository.getTotalOrder(id)*100.0)/10.0);
    }
}
