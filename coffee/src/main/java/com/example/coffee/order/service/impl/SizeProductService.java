package com.example.coffee.order.service.impl;

import com.example.coffee.order.model.SizeProduct;
import com.example.coffee.order.repository.ISizeProductRepository;
import com.example.coffee.order.service.ISizeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SizeProductService implements ISizeProductService {
    @Autowired
    private ISizeProductRepository sizeProductRepository;
    @Override
    public List<SizeProduct> getAll() {
        return sizeProductRepository.findAll();
    }
}
