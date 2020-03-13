package com.logines.schedule.controller;

import com.logines.schedule.model.Users;
import com.logines.schedule.service.SecurityService;
import com.logines.schedule.service.UserService;
import com.logines.schedule.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    private UserValidator userValidator;

    @Autowired
    private AuthenticationManager authenticationManager;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setValidator(userValidator);
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerForm", new Users());

        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @Valid Users userForm, BindingResult bindingResult) {
        model.addAttribute("userForm", userForm);
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage(Users users, Model model) {
        if(users != null) {
            model.addAttribute("user", users);
        }
        //userValidator.validate(users, bindingResult);

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") @Valid Users users, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "login";
        }else{
            return  "redirect:/";
        }
    }


        /*if (logout != null)
            model.addAttribute("message", "You have been logged out successfully."); */
        //model.addAttribute("jobs", securityService.findLoggedInUsername());

    @Autowired
    private void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }
}
