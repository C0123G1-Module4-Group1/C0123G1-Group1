package com.example.coffee.coupons.Service.impl;

import com.example.coffee.coupons.Service.ICouponsService;
import com.example.coffee.coupons.model.Coupons;
import com.example.coffee.coupons.repository.ICouponsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CouponsServiceImpl implements ICouponsService {
    @Autowired
    private ICouponsRepository iCouponsRepository;
    @Override
    public Page<Coupons> findAllCoupons(Pageable pageable) {
        return iCouponsRepository.findAllByDeleteStatusIsFalse(pageable);
    }

    @Override
    public Coupons findById(int id) {
        return iCouponsRepository.findById(id).get();
    }
}
