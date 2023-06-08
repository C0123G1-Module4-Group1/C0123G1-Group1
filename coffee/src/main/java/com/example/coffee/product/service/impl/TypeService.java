package com.example.coffee.product.service.impl;

import com.example.coffee.product.model.Product;
import com.example.coffee.product.model.TypeProduct;
import com.example.coffee.product.repository.ITypeRepository;
import com.example.coffee.product.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService implements ITypeService {
    @Autowired
    private ITypeRepository iTypeRepository;
    @Override
    public Page<TypeProduct> findAll(Integer page) {
        return iTypeRepository.findAll(PageRequest.of(page,10));
    }

    @Override
    public List<Product> typeProductBy(Integer id) {
        return iTypeRepository.typeProductBy(id);
    }


}
