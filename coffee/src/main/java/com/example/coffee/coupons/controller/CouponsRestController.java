package com.example.coffee.coupons.controller;

import com.example.coffee.coupons.Service.ICouponsService;
import com.example.coffee.coupons.model.Coupons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupons/")
@CrossOrigin("http://localhost:8080/coupons/")
public class CouponsRestController {
    @Autowired
    private ICouponsService iCouponsService;

    @GetMapping("/detail/{id}")
    public ResponseEntity<Coupons> viewDetailCoupons(@PathVariable("id")Integer id) {
        Coupons coupons=iCouponsService.findCoupons(id);
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }
}
