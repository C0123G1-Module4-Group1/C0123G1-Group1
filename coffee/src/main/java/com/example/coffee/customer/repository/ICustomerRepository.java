package com.example.coffee.customer.repository;

import com.example.coffee.customer.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findCustomerByDeleteStatusIsFalseAndEmail(String email);

    List<Customer> findCustomerByDeleteStatusIsFalseAndPhoneNumber(String phoneNumber);

    Page<Customer> findAllByDeleteStatusIsFalseOrderByIdDesc(Pageable pageable);

    Customer findCustomerByDeleteStatusIsFalseAndId(Integer id);

    @Query(value = "select * from customer as c where c.delete_status= 0 and c.email not in( select c.email from customer as c where c.delete_status = 0 and  c.email = :email)",nativeQuery = true)
    List<Customer> findCustomerByDeleteStatusIsFalseAndEmailAnd(@Param("email")String email);

    @Query("select c from customer as c where c.deleteStatus= false and c.phoneNumber not in( select c.phoneNumber from customer as c where c.deleteStatus = false and  c.phoneNumber = :phoneNumber)")
    List<Customer> findCustomerByDeleteStatusIsFalseAndPhoneNumbers(@Param("phoneNumber")String phoneNumber);
    @Query("select c from customer as c where c.deleteStatus=false and c.name like :name and c.phoneNumber like :phoneNumber and c.address like :address ")
    Page<Customer> findAllByName(@Param("name") String name, @Param("phoneNumber") String phoneNumber, @Param("address") String address, Pageable pageable);
}
