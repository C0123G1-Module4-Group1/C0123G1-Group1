package com.example.coffee.customer.service.impl;

import com.example.coffee.customer.model.Customer;
import com.example.coffee.customer.repository.ICustomerRepository;
import com.example.coffee.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Override
    public Page<Customer> findAllCustomer(Pageable pageable) {
        return iCustomerRepository.findAllByDeleteStatusIsFalseOrderByIdDesc(pageable);
    }

    @Override
    public Boolean checkExistEmail(String email) {
        List<Customer> customerList = iCustomerRepository.findCustomerByDeleteStatusIsFalseAndEmail(email);
        return customerList.isEmpty();
    }

    @Override
    public Boolean checkExistPhoneNumber(String phoneNumber) {
        List<Customer> customerList=iCustomerRepository.findCustomerByDeleteStatusIsFalseAndPhoneNumber(phoneNumber);
        return customerList.isEmpty();
    }


    @Override
    public Boolean createCustomer(Customer customer) {
        try {
            iCustomerRepository.save(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteCustomer(Integer id) {
        try {
            Customer customer = iCustomerRepository.findById(id).get();
            customer.setDeleteStatus(true);
            iCustomerRepository.save(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Customer findCustomer(Integer id) {
        return iCustomerRepository.findCustomerByDeleteStatusIsFalseAndId(id);
    }

    @Override
    public Boolean updateCustomer(Customer customer) {
        try {
            iCustomerRepository.save(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Page<Customer> findAllCustomerByNameOrPhoneNumberOrAddress(String nameSearch, String optionSearch, Pageable pageable) {
        String nameCustomer = "";
        String phoneNumber = "";
        String address = "";
        switch (optionSearch) {
            case "nameCustomer":
                nameCustomer = nameSearch;
                break;
            case "phoneNumber":
                phoneNumber = nameSearch;
                break;
            case "address":
                address = nameCustomer;
                break;
        }
        return iCustomerRepository.findAllByName('%' + nameCustomer + '%', '%' + phoneNumber + '%', '%' + address + '%', pageable);
    }
}
