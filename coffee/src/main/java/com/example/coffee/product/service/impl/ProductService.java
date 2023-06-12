package com.example.coffee.product.service.impl;

import com.example.coffee.product.model.Product;
import com.example.coffee.product.repository.IProductRepository;

import com.example.coffee.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;


    @Override
    public Optional<Product> findProduct(Integer id) {
        return iProductRepository.findById(id);
    }

    @Override
    public Product findProductById(Integer id) {
        return iProductRepository.findProductByStatusIsFalseAndId(id);
    }

    @Override
    public Page<Product> findAllByStatusIsFalse(Integer page) {
        return iProductRepository.findAllByStatusIsFalse(PageRequest.of(page, 5, Sort.by("id").descending()));
    }

    @Override
    public List<Product> getAll() {
        return iProductRepository.findAllByStatusIsFalse();
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
