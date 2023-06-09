package com.example.coffee.product.service.impl;

import com.example.coffee.product.model.Product;
import com.example.coffee.product.repository.IProductRepository;

import com.example.coffee.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;
    @Override
    public Page<Product> findAll(Integer page) {
        return iProductRepository.findAll(PageRequest.of(page, Integer.parseInt("4"), Sort.by("id").descending()));
    }

    @Override
    public List<Product> getAll() {
        return iProductRepository.findAll();
    }

    @Override
    public void save(Product product) {
        iProductRepository.save(product);
    }
}
