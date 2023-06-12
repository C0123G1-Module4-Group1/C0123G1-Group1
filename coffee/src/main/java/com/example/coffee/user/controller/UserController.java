package com.example.coffee.user.controller;

import com.example.coffee.user.model.User;
import com.example.coffee.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private IUserService iUserService;
    @GetMapping("/changePass")
    public String changePass(){
        return "user/change";
    }
    @PostMapping("/changePass")
    public String savePass(@RequestParam("pass")String pass, @RequestParam("newPass")String newPass, @RequestParam("newPassConfirm")String newPassConfirm, RedirectAttributes attributes,Authentication authentication,Model model){
        if (authentication.getName().equals("anonymousUser")){
            return "login/login";
        }
        if (!newPass.equals(newPassConfirm)){
            model.addAttribute("result","Confirmation password is not correct");
            return "user/change";
        }
         boolean check=iUserService.savePass(pass,newPass,authentication );
        if (!check){
            model.addAttribute("result","Incorrect password");
            return "user/change";
        }
        attributes.addFlashAttribute("flag", true);
        return "redirect:/staff";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login/login";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "login/login";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "login/403Page";
    }
}
