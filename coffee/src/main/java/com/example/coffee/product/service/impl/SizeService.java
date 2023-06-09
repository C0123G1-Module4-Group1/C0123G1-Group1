package com.example.coffee.product.service.impl;

import com.example.coffee.order.model.SizeProduct;
import com.example.coffee.product.repository.ISizeRepository;
import com.example.coffee.product.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SizeService implements ISizeService {
    @Autowired
    private ISizeRepository iSizeRepository;
    @Override
    public Page<SizeProduct> findAll(Integer page) {
        return iSizeRepository.findAll(PageRequest.of(page,10));
    }
}
