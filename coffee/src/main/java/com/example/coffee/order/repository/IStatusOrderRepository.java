package com.example.coffee.order.repository;

import com.example.coffee.order.model.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStatusOrderRepository extends JpaRepository<StatusOrder,Integer> {
}
