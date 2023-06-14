package com.example.coffee.user.controller;

import com.example.coffee.user.model.User;
import com.example.coffee.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/changePass")
    public String changePass() {
        return "user/change";
    }

    @PostMapping("/changePass")
    public String savePass(@RequestParam(value = "pass",defaultValue = "") String pass, @RequestParam(value = "newPass",defaultValue = "") String newPass, @RequestParam(value = "newPassConfirm",defaultValue = "") String newPassConfirm, RedirectAttributes attributes, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (newPass.equals("")){
            model.addAttribute("result", "Password can not be blank");
            return "user/change";
        }
        if (newPass.equals(newPassConfirm)) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, pass);
            try {
                authenticationManager.authenticate(token);
            } catch (BadCredentialsException e) {
                model.addAttribute("result", "Current password is incorrect");
                return "user/change";
            }
            boolean check = iUserService.savePass(pass, newPass, authentication);
            if (!check) {
                model.addAttribute("result", "Incorrect password");
                return "user/change";
            }
            attributes.addFlashAttribute("flag", true);
            return "redirect:/orderController/";

        } else {
            model.addAttribute("result", "New password and password confirmation do not match");
            return "user/change";
        }

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
