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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
        model.addAttribute("statusSearch", false);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        return "/customer/list";
    }

    @GetMapping("/create")
    public String showCustomerPage(Model model) {
        model.addAttribute("customerDTO", new CustomerDTO());
        return "/customer/create";
    }

    @PostMapping("/create-customer")
    public String createCustomer(@Validated @ModelAttribute("customerDTO") CustomerDTO customerDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        new CustomerDTO().validate(customerDTO, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "/customer/create";
        }
        boolean checkEmail = iCustomerService.checkExistEmail(customerDTO.getEmail());
        boolean checkPhoneNumber = iCustomerService.checkExistPhoneNumber(customerDTO.getPhoneNumber());
        if (checkEmail==false && checkPhoneNumber==false) {
            model.addAttribute("mess2", "Phone number already exists");
            model.addAttribute("mess1", "Email already exists");
            return "/customer/create";
        }
        if (checkEmail==false) {
            model.addAttribute("mess1", "Email already exists");
            return "/customer/create";
        }
        if (checkPhoneNumber==false) {
            model.addAttribute("mess2", "Phone number already exists");
            return "/customer/create";
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        boolean check = iCustomerService.createCustomer(customer);
        redirectAttributes.addFlashAttribute("check1", check);
        return "redirect:/customer/list";

    }

    @GetMapping("/delete/{id}")

    public String deleteCustomer(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        Boolean check = iCustomerService.deleteCustomer(id);
        redirectAttributes.addFlashAttribute("check2", check);
        return "redirect:/customer/list";
    }

    @GetMapping("/update/{id}")
    public String editCustomer(@PathVariable("id") Integer id, Model model, HttpServletResponse response) {
        Customer customer = iCustomerService.findCustomer(id);
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        model.addAttribute("customerDTO", customerDTO);
        return "/customer/edit";
    }

    @PostMapping("/update")
    public String updateCustomer(@Validated @ModelAttribute("customerDTO") CustomerDTO customerDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model) {
        new CustomerDTO().validate(customerDTO, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "/customer/edit";
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        boolean check = iCustomerService.updateCustomer(customer);
        redirectAttributes.addFlashAttribute("check3", check);
        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomerByName(@RequestParam(name = "nameSearch", defaultValue = "-1") String nameSearch, @RequestParam("optionSearch") String optionSearch,
                                       @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Page<Customer> customerPage = iCustomerService.findAllCustomerByNameOrPhoneNumberOrAddress(nameSearch, optionSearch, PageRequest.of(page, 5));
        if (customerPage.isEmpty()) {
            model.addAttribute("searchMess", "There is no data for search");
        }
        if (nameSearch.equals("-1")) {
            nameSearch = "";
        }
        int size = customerPage.getTotalPages();
        model.addAttribute("size", size);
        model.addAttribute("customerPage", customerPage);
        model.addAttribute("nameSearch", nameSearch);
        model.addAttribute("optionSearch", optionSearch);
        model.addAttribute("statusSearch", true);
        return "/customer/list";
    }

    @GetMapping("/detail/{id}")
    public String viewDetailCustomer(@PathVariable("id") Integer id, Model model) {
        Customer customer = iCustomerService.findCustomer(id);
        model.addAttribute("customer", customer);
        return "customer/vieew";
    }
}