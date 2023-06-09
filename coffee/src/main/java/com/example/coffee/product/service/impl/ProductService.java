package com.example.coffee.product.service.impl;

import com.example.coffee.product.model.Product;
import com.example.coffee.product.repository.IProductRepository;

import com.example.coffee.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public Page<Product> findAllByStatusIsFalse(Integer page) {
        return iProductRepository.findAllByStatusIsFalse(PageRequest.of(page, 5, Sort.by("id").descending()));
    }

    @Override
    public List<Product> getAll() {
        return iProductRepository.findAll();
    }

    @Override
    public boolean save(Product product) {
        iProductRepository.save(product);
        return true;
    }

    @Override
    public Product findById(Integer id) {
        return iProductRepository.findById(id).get();
    }

    @Override
    public boolean delete(Integer id) {
        Product product = findById(id);
        product.setStatus(true);
        iProductRepository.save(product);
        return true;
    }

    @Override
    public Page<Product> searchProduct( String name, Pageable pageable) {
        return iProductRepository.findAllByStatusIsFalse(name,pageable);
    }

    @Override
    public List<Product> findAllBySearchProduct(String name) {
        return iProductRepository.findAllByProduct(name);
    }
}
