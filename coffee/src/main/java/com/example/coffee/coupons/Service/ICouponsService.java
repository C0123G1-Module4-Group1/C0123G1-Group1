package com.example.coffee.coupons.Service;

import com.example.coffee.coupons.model.Coupons;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICouponsService {

    Page<Coupons> findAllCoupons(Pageable pageable);

    Coupons findCoupons(Integer id);

    Boolean createCoupons(Coupons coupons);

    Boolean deleteCoupons(Integer id);

    Boolean updateCoupon(Coupons coupons);

    Page<Coupons> findAllCouponsByCodeCoupons(String codeCoupons,Pageable pageable);


    List<Coupons> getAll(double total);

    Coupons findCouponsByProviso(double total);
}
