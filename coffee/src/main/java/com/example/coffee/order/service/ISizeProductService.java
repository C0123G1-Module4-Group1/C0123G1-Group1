package com.example.coffee.order.service;

import com.example.coffee.order.model.SizeProduct;

import java.util.List;

public interface ISizeProductService {
    List<SizeProduct> getAll();

    SizeProduct findById(Integer sizeProduct);

    SizeProduct findBySize(String sizeProduct);
}
