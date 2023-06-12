package com.example.coffee.product.repository;

import com.example.coffee.product.model.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeRepository extends JpaRepository<TypeProduct, Integer> {
}
