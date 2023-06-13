package com.example.coffee.product.repository;

import com.example.coffee.product.model.Product;
import com.example.coffee.product.model.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ITypeRepository extends JpaRepository<TypeProduct, Integer> {
    @Query(value = " FROM product AS p  WHERE p.typeProduct.id = :id and p.status=false")
    List<Product> findAllByStatusIsFalse(@Param("id")Integer id);

}
