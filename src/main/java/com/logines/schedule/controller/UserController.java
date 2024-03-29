package com.logines.schedule.controller;

import com.logines.schedule.model.Company;
import com.logines.schedule.model.Users;
import com.logines.schedule.model.WorkHour;
import com.logines.schedule.service.CompanyService;
import com.logines.schedule.service.SecurityService;
import com.logines.schedule.service.UserService;
import com.logines.schedule.service.WorkHourService;
import com.logines.schedule.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private AuthenticationManager authenticationManager;

    @InitBinder("user")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(userValidator);
    }

    @GetMapping("/register")
    public String getRegister(Model model, String error) {
        model.addAttribute("registerForm", new Users());
        List<Company> companyList = companyService.findAll();
        model.addAttribute("companyList", companyList);
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        model.addAttribute("login", new Users());
        if (error != null)
            model.addAttribute("error", "Your username or password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "user_login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Content-type=application/*")
    public String postRegister(Model model, @RequestBody @Valid @ModelAttribute("registerForm") Users registerForm, BindingResult bindingResult) {
        userValidator.validate(registerForm, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Something went wrong. Please check all credentials.");
            return "register";
        } else {
            userService.save(registerForm);
            //securityService.autoLogin(registerForm.getUsername(), registerForm.getPasswordConfirm());
            return "redirect:/";
        }
    }

    @GetMapping("/search-user/{username}")
    public String searchWorkHourByUsername(@PathVariable("username") String username, Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("usernameText", principal.getName());
        }
        model.addAttribute("searchString", username);
        Users users = userService.findByUsername(username);
        model.addAttribute("users", users);
        return "search_users_by_fullname";
    }


    @GetMapping("/search-user-by-fullname/{fullname}")
    public String searchWorkHourByFullname(@PathVariable("fullname") String username, Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("usernameText", principal.getName());
        }
        model.addAttribute("searchString", username);
        List<Users> users = userService.findByFullname(username);
        model.addAttribute("users", users);
        return "search_users_by_fullname";
    }
    @PostMapping("/edit-user/{id}")
    public String editUser(Model model, @Valid Users users,
                           BindingResult bindingResult) {
        if (userService.editUser(users)) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("error", bindingResult.getAllErrors());
                return "error_page";
            }
            model.addAttribute("message", "User deleted successfully...");
            return "successful_page";
        } else {
            model.addAttribute("message", "User " + users.getUsername() + " edited successfully.");
            return "successful_page";
        }
    }

    @PostMapping("/delete-user/{username}")
    public String deleteUser(Model model, @PathVariable("username") String usernsame) {
        userService.deleteUser(usernsame);
        model.addAttribute("message", "User" + usernsame + " deleteted successfully...");
        return "successful_page";
    }

    @GetMapping("/404")
    public String pageNotFound() {
        return "404";
    }

    //We don't define login it is defined by spring security already

    @Autowired
    private void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }
}
