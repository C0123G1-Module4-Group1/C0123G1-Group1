package com.example.coffee.product.service;

import com.example.coffee.product.model.Product;
import com.example.coffee.product.model.TypeProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITypeService {
    Page<TypeProduct> findAll(Integer page);
    List<Product> findAllByStatusIsFalse(Integer id);
    boolean save (TypeProduct typeProduct);
    boolean delete (Integer id);
    TypeProduct findById(Integer id);
    List<TypeProduct> findAllByTypeProduct( String name);


}
