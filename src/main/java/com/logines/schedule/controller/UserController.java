package com.logines.schedule.controller;

import com.logines.schedule.model.Users;
import com.logines.schedule.service.SecurityService;
import com.logines.schedule.service.UserService;
import com.logines.schedule.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setValidator(userValidator);
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("registerForm", new Users());
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        model.addAttribute("userForm", new Users());
        LOG.error("ERROR::::::::::::::"  + error);
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute("registerForm") Users userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        LOG.error("ERROR:::::::::::::: "  + bindingResult.hasErrors());

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/";
    }

    @PostMapping("/login")
    public String postLogin(@Valid @ModelAttribute("userForm") Users users, BindingResult bindingResult) {
        userValidator.validate(users, bindingResult);
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
