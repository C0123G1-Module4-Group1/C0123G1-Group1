package com.example.coffee.customer.repository;

import com.example.coffee.customer.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("select c from customer as c where c.deleteStatus =false")
    Page<Customer> findAllByDeleteStatusIsFalse(Pageable pageable);

    Customer findCustomerByDeleteStatusIsFalseAndId(Integer id);

    @Query("select c from customer as c where c.deleteStatus=false and c.name like :name and c.phoneNumber like :phoneNumber and c.address like :adress ")
    Page<Customer> findAllByName(@Param("name")String name,@Param("phoneNumber")String phoneNumber,@Param("adress")String adress,Pageable pageable);
}
