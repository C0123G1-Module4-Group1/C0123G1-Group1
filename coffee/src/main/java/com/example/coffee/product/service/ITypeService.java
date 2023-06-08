package com.example.coffee.product.service;

import com.example.coffee.product.model.Product;
import com.example.coffee.product.model.TypeProduct;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ITypeService {
    Page<TypeProduct> findAll(Integer page);
    List<Product> typeProductBy(Integer id);


}
