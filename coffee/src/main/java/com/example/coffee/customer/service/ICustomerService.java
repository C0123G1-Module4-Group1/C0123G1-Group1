package com.example.coffee.customer.service;

import com.example.coffee.customer.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    Page<Customer> findAllCustomer(Pageable pageable);

    Boolean checkExistEmail(String email);
    Boolean checkExistPhoneNumber(String phoneNumber);
    Boolean createCustomer(Customer customer);

    Boolean deleteCustomer(Integer id);

    Customer findCustomer(Integer id);

    Boolean updateCustomer(Customer customer);

    Page<Customer> findAllCustomerByNameOrPhoneNumberOrAddress(String nameSearch,String optionSearch,Pageable pageable);
    boolean findAllCustomerNotInEmail(String email,Integer id);
    boolean findAllCustomerNotInPhone(String phoneNumber,Integer id);
}
