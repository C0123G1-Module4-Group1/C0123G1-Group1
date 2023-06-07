package com.example.coffee.product.service;

import com.example.coffee.product.model.SizeProduct;
import com.example.coffee.product.model.TypeProduct;
import com.sun.javafx.css.Size;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISizeService {
    Page<SizeProduct> findAll(Integer page);
}
