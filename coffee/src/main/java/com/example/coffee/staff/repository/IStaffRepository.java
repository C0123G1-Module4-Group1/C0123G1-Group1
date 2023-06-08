package com.example.coffee.staff.repository;

import com.example.coffee.staff.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStaffRepository extends JpaRepository<Staff,Integer> {
}
