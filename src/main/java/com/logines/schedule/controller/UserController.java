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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userForm", new Users());

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid Users users,
                           BindingResult bindingResult,
                           Model model) {
        userValidator.validate(users, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

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


}
