package com.example.coffee.order.repository;

import com.example.coffee.order.model.SizeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISizeProductRepository extends JpaRepository<SizeProduct,Integer> {
}
