package com.example.coffee.product.repository;


import com.example.coffee.product.model.SizeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISizeRepository extends JpaRepository<SizeProduct, Integer> {
}
