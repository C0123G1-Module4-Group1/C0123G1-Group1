package com.example.coffee.customer.controller;

import com.example.coffee.customer.model.Customer;
import com.example.coffee.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/customer")
@CrossOrigin("http://localhost:8080/customer/")
public class CustomerRestController {
    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping("/detail/{id}")
    public ResponseEntity<Customer> pagingCustomerList(@PathVariable("id")Integer id) {
        Customer customer=iCustomerService.findCustomer(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
