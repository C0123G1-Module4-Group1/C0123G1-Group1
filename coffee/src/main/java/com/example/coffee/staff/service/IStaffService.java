package com.example.coffee.staff.service;

import com.example.coffee.staff.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStaffService {
    Page<Staff> findAll(int page);

    boolean saveNew(Staff staff);
    void save(Staff staff);

    Staff findById(Integer id);

    Page<Staff> findAllByName(String name, Pageable page);
}
