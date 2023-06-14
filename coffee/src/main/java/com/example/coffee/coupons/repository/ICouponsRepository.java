package com.example.coffee.coupons.repository;

import com.example.coffee.coupons.model.Coupons;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICouponsRepository extends JpaRepository<Coupons, Integer> {
    Page<Coupons> findAllByDeleteStatusIsFalseOrderByIdDesc(Pageable pageable);

    Coupons findCouponsByDeleteStatusIsFalseAndId(Integer id);

    @Query("select c from coupons as c where c.deleteStatus=false and c.codeCoupons like :codeCoupons")
    Page<Coupons> findAllByDeleteStatusIsFalseAndCodeCoupons(@Param("codeCoupons")String codeCoupons, Pageable pageable);
}
