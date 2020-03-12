package com.logines.schedule.controller;

import com.logines.schedule.model.Users;
import com.logines.schedule.service.SecurityService;
import com.logines.schedule.service.UserService;
import com.logines.schedule.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String register( @ModelAttribute Users users,
                           BindingResult bindingResult) {
        userValidator.validate(users, bindingResult);
        System.out.println("Username" + users.getUsername());


        /*if (bindingResult.hasErrors()) {


            System.out.print("Error");
            return "register";
        }*/

        userService.save(users);

        securityService.autoLogin(users.getUsername(), users.getPasswordConfirm());

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        model.addAttribute("jobs", securityService.findLoggedInUsername());

        return "login";
    }

    @Autowired
    private void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }
}
