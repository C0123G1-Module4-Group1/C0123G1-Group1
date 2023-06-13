package com.example.coffee.product.repository;


import com.example.coffee.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAllByStatusIsFalse(Pageable pageable);


    //    @Query(value = "update product set status= 1 where id= :id")
//    @Modifying
//    @Transactional
//    void deleteById(@Param("id") Integer id);
    @Query(value = " SELECT p FROM product AS p  WHERE p.name LIKE concat('%',:name,'%')")
    Page<Product> findAllByStatusIsFalse(@Param("name") String name, Pageable pageable);

    @Query(value = " SELECT p FROM product AS p  WHERE p.name LIKE concat('%',:name,'%')")
    List<Product> findAllByProduct(@Param("name") String name);
    Product findProductByStatusIsFalseAndId(Integer id);

}
