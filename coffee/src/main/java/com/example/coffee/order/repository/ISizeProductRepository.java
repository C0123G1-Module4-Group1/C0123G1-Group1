package com.example.coffee.order.repository;

import com.example.coffee.order.model.SizeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ISizeProductRepository extends JpaRepository<SizeProduct,Integer> {
    @Query(value = "SELECT * FROM size WHERE size = :sizeProduct",nativeQuery = true)
    SizeProduct findBySize(@Param("sizeProduct") String sizeProduct);
}
