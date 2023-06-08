package com.example.coffee.customer.controller;

import com.example.coffee.customer.model.Customer;
import com.example.coffee.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/customer")
@CrossOrigin("http://localhost:8080/customer")
public class CustomerRestController {
    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping("/paging-list")
    public ResponseEntity<Page<Customer>> pagingCustomerList(@RequestParam(value = "page", defaultValue = "0") Integer page) {
        Page<Customer> customerPage = iCustomerService.findAllCustomer(PageRequest.of(page, 5));
        return new ResponseEntity<>(customerPage, HttpStatus.OK);
    }


}
