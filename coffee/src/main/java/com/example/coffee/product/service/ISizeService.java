package com.example.coffee.product.service;

import com.example.coffee.order.model.SizeProduct;
import org.springframework.data.domain.Page;

public interface ISizeService {
    Page<SizeProduct> findAll(Integer page);
}
