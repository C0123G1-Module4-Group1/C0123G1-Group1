package com.example.coffee.coupons.Service.impl;

import com.example.coffee.coupons.Service.ICouponsService;
import com.example.coffee.coupons.model.Coupons;
import com.example.coffee.coupons.repository.ICouponsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CouponsServiceImpl implements ICouponsService {
    @Autowired
    private ICouponsRepository iCouponsRepository;

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean updateCoupon(Coupons coupons) {
        try {
            iCouponsRepository.save(coupons);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Page<Coupons> findAllCouponsByCodeCoupons(String codeCoupons,Pageable pageable) {
        return iCouponsRepository.findAllByDeleteStatusIsFalseAndCodeCoupons(codeCoupons,pageable);
    }

    @Override
    public List<Coupons> getAll(double total) {
        return iCouponsRepository.getAll(total);
    }

    @Override
    public Float findCouponsByProviso(double total) {
        Float coupons = iCouponsRepository.findCouponsByProviso(total);
        if(coupons != null){
            return coupons;
        }else {
            return 0f;
        }

    }

    @Override
    public Page<Coupons> findAllCoupons(Pageable pageable) {
        return iCouponsRepository.findAllByDeleteStatusIsFalseOrderByIdDesc(pageable);
    }

    @Override
    public Coupons findCoupons(Integer id) {
        return iCouponsRepository.findCouponsByDeleteStatusIsFalseAndId(id);
    }

    @Override
    public Boolean createCoupons(Coupons coupons) {
        try {
            iCouponsRepository.save(coupons);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteCoupons(Integer id) {
        try {
            Coupons coupons = findCoupons(id);
            coupons.setDeleteStatus(true);
            iCouponsRepository.save(coupons);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
