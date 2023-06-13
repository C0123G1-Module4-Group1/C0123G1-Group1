package com.example.coffee.product.service;


import com.example.coffee.product.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    Product findProductById(Integer id);

    Page<Product> findAll(Integer page);

    List<Product> getAll();

    void save(Product product);
}
