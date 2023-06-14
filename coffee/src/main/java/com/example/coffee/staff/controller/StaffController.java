package com.example.coffee.staff.controller;

import com.example.coffee.staff.dto.StaffDto;
import com.example.coffee.staff.model.Staff;
import com.example.coffee.staff.service.IStaffService;
import com.example.coffee.user.dto.UserDto;
import com.example.coffee.user.model.SecurityUtils;
import com.example.coffee.user.model.User;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MimeHeaders;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private IStaffService iStaffService;

    @GetMapping("")
    public String getAll(Model model, @RequestParam(value = "page", defaultValue = "0") int page, HttpServletResponse httpServletResponse) {
        Page<Staff> staffList = iStaffService.findAll(page);
        model.addAttribute("staffList", staffList);
        httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        return "staff/staffList";
    }

    @GetMapping("/createStaff")
    public String createStaff(Model model) {
        UserDto userDto=new UserDto();
        StaffDto staffDto=new StaffDto();
        staffDto.setUserDto(userDto);
        model.addAttribute("staff", staffDto);
        return "staff/createStaff";
    }

    @PostMapping()
    public String save(@Validated @ModelAttribute("staff") StaffDto staffDto, BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model) {
        if (bindingResult.hasErrors()) {
            return "staff/createStaff";
        }
        Staff staff = new Staff();
        User user = new User();
        BeanUtils.copyProperties(staffDto, staff);
        BeanUtils.copyProperties(staffDto.getUserDto(), user);
        staff.setUser(user);
        boolean check=true;
        check= iStaffService.saveNew(staff);
        if (!check){
            model.addAttribute("result","Email or phone number or account is duplicated!!!!");
            return "staff/createStaff";
        }
        redirectAttributes.addFlashAttribute("mess", true);
        return "redirect:/staff";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, Model model) {
        Staff staff = iStaffService.findById(id);
        StaffDto staffDto = new StaffDto();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(staff.getUser(), userDto);
        userDto.setRole(staff.getUser().getRole());
        staffDto.setUserDto(userDto);
        BeanUtils.copyProperties(staff, staffDto);
        model.addAttribute("staff", staffDto);
        return "staff/updateStaff";
    }

    @PostMapping("/update")
    public String saveStaffUpdate(@Validated @ModelAttribute("staff") StaffDto staffDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "staff/updateStaff";
        }
        Staff staff = new Staff();
        BeanUtils.copyProperties(staffDto, staff);
        iStaffService.save(staff);
        redirectAttributes.addFlashAttribute("flag", true);
        return "redirect:/staff";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes attributes) {
        Staff staff = iStaffService.findById(id);
        staff.setDeleteStatus(true);
        staff.getUser().setDeleteStatus(true);
        iStaffService.save(staff);
        attributes.addFlashAttribute("check", true);
        return "redirect:/staff";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Integer id, Model model) {
        Staff staff = iStaffService.findById(id);
        model.addAttribute("staff", staff);
        return "staff/view";
    }

    @PostMapping("/search")
    public String search(@RequestParam("name") String name, @RequestParam(value = "page", defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 9);
        Page<Staff> staffList = iStaffService.findAllByName(name, pageable);
        model.addAttribute("staffList", staffList);
        model.addAttribute("name", name);
        return "staff/staffList";
    }


}
