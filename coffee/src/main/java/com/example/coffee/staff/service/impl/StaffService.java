package com.example.coffee.staff.service.impl;

import com.example.coffee.staff.model.Staff;
import com.example.coffee.staff.repository.IStaffRepository;
import com.example.coffee.staff.service.IStaffService;
import com.example.coffee.user.model.Role;
import com.example.coffee.user.model.User;
import com.example.coffee.user.repository.IRoleRepository;
import com.example.coffee.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StaffService implements IStaffService {
    @Autowired
    private IStaffRepository iStaffRepository;
    @Autowired
    private IRoleRepository iRoleRepository;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Page<Staff> findAll(int page) {
        return iStaffRepository.findAllByDeleteStatusIsFalse(PageRequest.of(page,7));
    }
    @Transactional(rollbackOn = Throwable.class)
    @Override
    public boolean saveNew(Staff staff) {
        try {
            List<Staff> staffList=iStaffRepository.findAll();
            List<User> users=iUserRepository.findAll();
            Role role=iRoleRepository.getReferenceById(2);
            User user=staff.getUser();
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getAccount().equals(user.getAccount())){
                    return false;
                }
            }
            for (int i = 0; i < staffList.size(); i++) {
                if (staffList.get(i).getEmail().equals(staff.getEmail()) || staffList.get(i).getPhoneNumber().equals(staff.getPhoneNumber())){
                    return false;
                }
            }
            user.setRole(role);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setDeleteStatus(false);
            staff.setDeleteStatus(false);
            iUserRepository.save(user);
            iStaffRepository.save(staff);
        }catch (Exception e){
            return false;
        }
return true;
    }

    @Override
    public void save(Staff staff) {
        iStaffRepository.save(staff);
    }

    @Override
    public Staff findById(Integer id) {
        return iStaffRepository.findById(id).get();
    }

    @Override
    public Page<Staff> findAllByName(String name,Pageable page) {
        Page<Staff> staffList=iStaffRepository.findByNameStaff(name,page);
        return staffList;
    }
}
