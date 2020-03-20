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

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private AuthenticationManager authenticationManager;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(userValidator);
    }

    @GetMapping("/register")
    public String getRegister(Model model, String error) {
        model.addAttribute("registerForm", new Users());

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        return "register";
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        model.addAttribute("login", new Users());
        if (error != null)
            model.addAttribute("error", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "user-login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "user-login";
    }

    @RequestMapping(value="/register", method=RequestMethod.POST, headers = "Content-type=application/*")
    public String postRegister(Model model, @RequestBody @Valid @ModelAttribute("registerForm") Users registerForm, BindingResult bindingResult) {
        userValidator.validate(registerForm, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Something went wrong. Please check all credentials.");
            model.addAttribute("status", "Something went wrong:" + bindingResult.getModel().get("username"));
            return "register";
        } else {
            userService.save(registerForm);
            securityService.autoLogin(registerForm.getUsername(), registerForm.getPasswordConfirm());
            return "redirect:/";
        }
    }

    @RequestMapping(value="/user-login", method=RequestMethod.POST, headers = "Content-type=application/*")
    public String userLogin(Model model, @RequestBody @Valid @ModelAttribute("user") Users loginForm, BindingResult bindingResult) {
        userValidator.validate(loginForm, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println("USER LOGIN FAILED");
            model.addAttribute("help", bindingResult.getAllErrors());
            return "register";
        } else {
            System.out.println("USER LOGIN");
            securityService.autoLogin(loginForm.getUsername(), loginForm.getPassword());
            return "redirect:/welcome";
        }
    }

    @GetMapping("/404")
    public String pageNotFound(){
        return "404";
    }

    //We don't define login it is defined by spring security already

    @Autowired
    private void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }
}
