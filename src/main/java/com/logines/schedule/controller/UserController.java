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
    public String register(@ModelAttribute("userForm") Users userForm, BindingResult bindingResult, Model model) {
        //model.addAttribute("users", userForm);
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage(Model model, BindingResult bindingResult, String logout) {
        model.addAttribute("loginForm", new Users());
        if (bindingResult.hasErrors()){
            return "login";
        }

        /*if (logout != null)
            model.addAttribute("message", "You have been logged out successfully."); */
        //model.addAttribute("jobs", securityService.findLoggedInUsername());

        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid Users users, Model model, BindingResult bindingResult, String logout) {
        model.addAttribute("users", users);
        if(bindingResult.hasErrors()) {

        }else{

        }
        return "login";
    }


        /*if (logout != null)
            model.addAttribute("message", "You have been logged out successfully."); */
        //model.addAttribute("jobs", securityService.findLoggedInUsername());

    @Autowired
    private void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }
}
