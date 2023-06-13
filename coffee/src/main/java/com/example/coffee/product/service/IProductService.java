package com.example.coffee.product.service;


import com.example.coffee.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface IProductService {

    Product findProductById(Integer id);
    Product findById(Integer id);

    Page<Product> findAllByStatusIsFalse(Integer page);

    boolean save(Product product);

    boolean delete(Integer id);

    Page<Product> searchProduct(String name, Pageable pageable);

    List<Product> findAllBySearchProduct(String name);
    List<Product> getAll();

}
