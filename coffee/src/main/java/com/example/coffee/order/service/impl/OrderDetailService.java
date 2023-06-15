package com.example.coffee.order.service.impl;

import com.example.coffee.order.dto.CartItem;
import com.example.coffee.order.model.Order;
import com.example.coffee.order.model.OrderDetail;
import com.example.coffee.order.model.SizeProduct;
import com.example.coffee.order.repository.IOrderDetailRepository;
import com.example.coffee.order.service.IOderDetailService;
import com.example.coffee.order.service.IOrderService;
import com.example.coffee.order.service.ISizeProductService;
import com.example.coffee.product.model.Product;
import com.example.coffee.product.service.IProductService;
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
    @Autowired
    private IProductService productService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private ISizeProductService sizeProductService;

    @Override
    public Page<OrderDetail> findAllBiIdOrder(Integer id, Integer page) {
        return orderDetailRepository.findAllBiIdOrder(id, PageRequest.of(page, 5, Sort.by("id").descending()));
    }

    public void saveOrderDetail(Integer idOrder, OrderDetail orderDetail) {
//        orderDetailRepository.save(orderDetail);
    }

    @Override
    public Long getTotalOrder(Integer id) {
        Long total = orderDetailRepository.getTotalOrder(id);
        if (total != null) {
            return total;
        } else {
            return 0L;
        }
    }

    @Override
    public boolean addOrderDetail(Map<Integer, CartItem> productList, Integer idOrder) {
        try {
            for (Map.Entry<Integer, CartItem> p : productList.entrySet()) {
                Product product = productService.findById(p.getValue().getIdProduct());
                Order order = orderService.findById(idOrder);
                SizeProduct size = sizeProductService.findBySize(p.getValue().getSizeProduct());
                OrderDetail orderDetail = new OrderDetail(order, product, size, p.getValue().getPriceProduct(), p.getValue().getQuantity());
                orderDetailRepository.save(orderDetail);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
