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
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CouponsServiceImpl implements ICouponsService {
    @Autowired
    private ICouponsRepository iCouponsRepository;

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean updateCoupon(Coupons coupons) {
        try {
            iCouponsRepository.save(coupons);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Page<Coupons> findAllCouponsByCodeCoupons(String codeCoupons, Pageable pageable) {
        return iCouponsRepository.findAllByDeleteStatusIsFalseAndCodeCoupons(codeCoupons, pageable);
    }

    @Override
    public List<Coupons> getAll(double total) {
        return iCouponsRepository.getAll(total);
    }

    @Override
    public Float findCouponsByProviso(double total) {
        Float coupons = iCouponsRepository.findCouponsByProviso(total);
        if (coupons != null) {
            return coupons;
        } else {
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
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String createCodeName() {
        String result = "";
        boolean check = true;
        do {
            Integer codeName = ThreadLocalRandom.current().nextInt(1, 99999);
            if (codeName < 10000) {
                result = "CP-0" + codeName;
            } else if (codeName < 1000) {
                result = "CP-00" + codeName;
            } else if (codeName < 100) {
                result = "CP-000" + codeName;
            } else if (codeName < 10) {
                result = "CP-0000" + codeName;
            } else {
                result = "CP-" + codeName;
            }
            Optional<Coupons> coupons = iCouponsRepository.findCouponsByDeleteStatusIsFalseAndCodeCoupons(result);
            if (coupons.isPresent()) {
                check = false;
            }
        } while (!check);
        return result;
    }
}
