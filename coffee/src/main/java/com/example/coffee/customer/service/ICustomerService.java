package com.example.coffee.customer.service;

import com.example.coffee.customer.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService {
    Page<Customer> findAllCustomer(Pageable pageable);

    Boolean createCustomer(Customer customer);

    Boolean deleteCustomer(Integer id);

    Customer findCustomer(Integer id);

    Boolean updateCustomer(Customer customer);

    Page<Customer> findAllCustomerByNameOrPhoneNumberOrAddress(String nameSearch,String optionSearch,Pageable pageable);
}
