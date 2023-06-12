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
    public Page<Product> findAll(Integer page) {
        return iProductRepository.findAll(PageRequest.of(page, Integer.parseInt("5"), Sort.by("id").descending()));
    }

    @Override
    public List<Product> getAll() {
        return iProductRepository.findAllByStatusIsFalse();
    }

    @Override
    public void save(Product product) {
        iProductRepository.save(product);
    }
}
