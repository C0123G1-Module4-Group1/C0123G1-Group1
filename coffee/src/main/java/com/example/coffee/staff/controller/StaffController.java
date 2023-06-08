package com.example.coffee.staff.controller;

import com.example.coffee.staff.model.Staff;
import com.example.coffee.staff.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private IStaffService iStaffService;
    @GetMapping("")
    public String getAll(Model model,@RequestParam(value = "page", defaultValue = "0") int page){
        Page<Staff> staffList=iStaffService.findAll(page);
        model.addAttribute("staffList",staffList);
        return "staff/staff";
    }
    @GetMapping("/createStaff")
    public String createStaff(Model model){
        model.addAttribute("staff",new Staff());
        return "staff/createStaff";
    }
    @PostMapping()
    public String save(@ModelAttribute("staff") Staff staff, RedirectAttributes redirectAttributes){
        iStaffService.saveNew(staff);
        redirectAttributes.addFlashAttribute("mess",true);
        return "redirect:/staff";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")Integer id,Model model){
        Staff staff=iStaffService.findById(id);
        model.addAttribute("staff",staff);
        return "staff/updateStaff";
    }
    @PostMapping("/update")
    public String saveStaffUpdate(@ModelAttribute("staff") Staff staff, RedirectAttributes redirectAttributes){

        iStaffService.save(staff);
        redirectAttributes.addFlashAttribute("flag",true);
        return "redirect:/staff";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id")Integer id){
        Staff staff=iStaffService.findById(id);
        staff.setDeleteStatus(true);
        staff.getUser().setDeleteStatus(true);
        iStaffService.save(staff);
        return "redirect:/staff";
    }
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id")Integer id,Model model){
        Staff staff=iStaffService.findById(id);
        model.addAttribute("staff",staff);
        return "staff/view";
    }
}
