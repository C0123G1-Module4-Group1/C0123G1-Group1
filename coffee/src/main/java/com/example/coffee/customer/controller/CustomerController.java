package com.example.coffee.customer.controller;

import com.example.coffee.customer.dto.CustomerDTO;
import com.example.coffee.customer.model.Customer;
import com.example.coffee.customer.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping("/list")
    public String showList(@RequestParam(value = "page", defaultValue = "0") Integer page, Model model, HttpServletResponse response) {
        Page<Customer> customerPage = iCustomerService.findAllCustomer(PageRequest.of(page, 5));
        model.addAttribute("customerPage", customerPage);
        model.addAttribute("statusSearch",false);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        return "/customer/list";
    }

    @GetMapping("/create")
    public String showCustomerPage(Model model) {
        model.addAttribute("customerDTO", new CustomerDTO());
        return "/customer/create";
    }

    @PostMapping("/create-customer")
    public String createCustomer(@ModelAttribute("customerDto") CustomerDTO customerDTO, RedirectAttributes redirectAttributes) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        boolean check = iCustomerService.createCustomer(customer);
        redirectAttributes.addFlashAttribute("check", check);
        redirectAttributes.addFlashAttribute("customerDTO", customerDTO);
        return "redirect:/customer/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        Boolean check = iCustomerService.deleteCustomer(id);
        redirectAttributes.addFlashAttribute("check", check);
        return "redirect:/customer/list";
    }

    @GetMapping("/update/{id}")
    public String editCustomer(@PathVariable("id") Integer id, Model model) {
        Customer customer = iCustomerService.findCustomer(id);
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        model.addAttribute("customerDTO", customerDTO);
        return "/customer/edit";
    }

    @PostMapping("/update-customer")
    public String updateCustomer(@ModelAttribute("customerDto") CustomerDTO customerDTO, RedirectAttributes redirectAttributes) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        boolean check = iCustomerService.updateCustomer(customer);
        redirectAttributes.addFlashAttribute("check", check);
        redirectAttributes.addFlashAttribute("customerDTO", customerDTO);
        return "redirect:/customer/edit";
    }

    @GetMapping("/search")
    public String searchCustomerByName(@RequestParam("nameSearch") String nameSearch,@RequestParam("optionSearch") String optionSearch,
                                       @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Page<Customer> customerPage = iCustomerService.findAllCustomerByNameOrPhoneNumberOrAdress(nameSearch,optionSearch, PageRequest.of(page, 5));
        model.addAttribute("customerPage", customerPage);
        model.addAttribute("nameSearch",nameSearch);
        model.addAttribute("optionSearch",optionSearch);
        model.addAttribute("statusSearch",true);
        return "/customer/list";
    }
}
