package com.example.coffee.order.repository;

import com.example.coffee.order.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Integer> {
    //@Query(value = "SELECT * FROM orders WHERE status_delete = 0",nativeQuery = true)
    Page<Order> findAllByDeleteStatusIsFalse(PageRequest id);

    //    @Query(value = "UPDATE orders SET status_delete = 1 WHERE id = :id", nativeQuery = true)
//    void setDeleteStatusToTrue(@Param("id") Integer id);
    @Query(value = "UPDATE Order SET deleteStatus = 1 WHERE id = :id")
    @Modifying
    @Transactional
    boolean setDeleteStatusToTrue(@Param("id") Integer id);

    @Query(value = "SELECT * FROM orders WHERE id LIKE :id", nativeQuery = true)
    Page<Order> findAllByIdContainingAndDeleteStatusIsFalse(@Param("id") Integer id, PageRequest id1);

    @Query(value = "SELECT * FROM orders WHERE id = :id AND status_delete = 1", nativeQuery = true)
    Order findByIdOrder(@Param("id") String id);
}
