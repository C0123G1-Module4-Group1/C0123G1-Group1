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
    public List<Product> findAllByStatusIsFalse(Integer id) {
        return iTypeRepository.findAllByStatusIsFalse(id);
    }

    @Override
    public boolean save(TypeProduct typeProduct) {
        iTypeRepository.save(typeProduct);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        iTypeRepository.delete(findById(id));
        return true;
    }

    @Override
    public TypeProduct findById(Integer id) {
        return iTypeRepository.findById(id).get();
    }


}
