package com.example.coffee.product.service;


import com.example.coffee.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductService {
    Page<Product> findAllByStatusIsFalse(Integer page);

    List<Product> getAll();

    boolean save(Product product);

    Product findById(Integer id);

    boolean delete(Integer id);
    Page<Product>searchProduct(String name, Pageable pageable);

}
