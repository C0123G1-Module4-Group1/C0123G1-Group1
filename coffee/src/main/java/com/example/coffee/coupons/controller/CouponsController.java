package com.example.coffee.coupons.controller;

import com.example.coffee.coupons.Service.ICouponsService;
import com.example.coffee.coupons.model.Coupons;
import com.example.coffee.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

//@Controller
@RequestMapping("/coupons")
public class CouponsController {
//    @Autowired
    private ICouponsService iCouponsService;

    @GetMapping("/list")
    public String showList(@RequestParam(value = "page", defaultValue = "0") Integer page, Model model, HttpServletResponse response) {
        Page<Coupons> couponsPage = iCouponsService.findAllCoupons(PageRequest.of(page, 5));
        model.addAttribute("couponsPage",couponsPage );
        model.addAttribute("statusSearch",false);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        return "/coupons/list";
    }
}
