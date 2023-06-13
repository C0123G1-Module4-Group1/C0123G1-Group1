package com.example.coffee.order.repository;

import com.example.coffee.order.model.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query(value ="SELECT * FROM order_detail where id_order = :id",nativeQuery = true)
    Page<OrderDetail> findAllBiIdOrder(@Param("id") Integer id, PageRequest page);
    @Query(value = "SELECT 1 + SUM(od.price_product * od.quantity * s.rate) * (2.0 - c.`value`) AS total_price FROM order_detail AS od LEFT JOIN size AS s ON od.id_size = s.id LEFT JOIN orders AS o ON od.id_order = o.id LEFT JOIN coupons AS c ON o.id_coupons = c.id GROUP BY od.id_order HAVING od.id_order = :id",nativeQuery = true)
    Long getTotalOrder(@Param("id") Integer id);

}
