package com.example.coffee.coupons.repository;

import com.example.coffee.coupons.model.Coupons;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICouponsRepository extends JpaRepository<Coupons,Integer> {
    Page<Coupons> findAllByDeleteStatusIsFalse(Pageable pageable);
}
