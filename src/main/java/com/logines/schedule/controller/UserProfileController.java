package com.logines.schedule.controller;

import com.logines.schedule.model.UserProfile;
import com.logines.schedule.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserProfileController {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/add-user-details")
    public String addUserDetails(@Valid UserProfile userProfile,
                       BindingResult bindingResult,
                       Model model){
        userDetailsService.addUserDetails(userProfile);

        return "user_details_added_successfully";
    }
}
