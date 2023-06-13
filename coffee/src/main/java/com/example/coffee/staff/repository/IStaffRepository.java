package com.example.coffee.staff.repository;


import com.example.coffee.staff.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IStaffRepository extends JpaRepository<Staff,Integer> {
    @Query(value = "select s from Staff s where s.name like  concat( '%',:name,'%') and s.deleteStatus=false ")
    Page<Staff> findByNameStaff(@Param("name") String name, Pageable pageable);
    Page<Staff> findAllByDeleteStatusIsFalse(PageRequest pageRequest);
}
