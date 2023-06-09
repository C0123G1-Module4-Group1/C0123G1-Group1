package com.example.coffee.order.controller;

import com.example.coffee.order.service.IOderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin/orderDetail")
public class OrderRestController {
    @Autowired
    private IOderDetailService oderDetailService;
//@GetMapping("/detail/{id}")
//    public String detailOrder(@PathVariable Integer id,)
}
