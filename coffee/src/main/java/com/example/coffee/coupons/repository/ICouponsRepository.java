package com.example.coffee.coupons.repository;

import com.example.coffee.coupons.model.Coupons;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICouponsRepository extends JpaRepository<Coupons, Integer> {
    Page<Coupons> findAllByDeleteStatusIsFalseOrderByIdDesc(Pageable pageable);

    Coupons findCouponsByDeleteStatusIsFalseAndId(Integer id);

    @Query("select c from coupons as c where c.deleteStatus=false and c.codeCoupons like :codeCoupons")
    Page<Coupons> findAllByDeleteStatusIsFalseAndCodeCoupons(@Param("codeCoupons") String codeCoupons, Pageable pageable);

    @Query(value = "SELECT * FROM coupons WHERE (proviso * 1000) <= :total AND delete_status = 0", nativeQuery = true)
    List<Coupons> getAll(double total);

    @Query(value = "SELECT * FROM coupons WHERE (proviso*1000) <= :total AND delete_status = 0 AND begin_time < now() AND end_time > now()", nativeQuery = true)
    Coupons findCouponsByProviso(@Param("total") double total);
}
