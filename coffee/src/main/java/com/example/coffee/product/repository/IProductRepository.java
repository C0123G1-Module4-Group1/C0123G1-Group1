package com.example.coffee.product.repository;


import com.example.coffee.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository  extends JpaRepository<Product, Integer> {
    Product findProductByStatusIsFalseAndId(Integer id);

    List<Product> findAllByStatusIsFalse();
}
