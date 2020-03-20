package com.logines.schedule.controller;

import com.logines.schedule.model.Class;
import com.logines.schedule.model.Job;
import com.logines.schedule.model.UserDetails;
import com.logines.schedule.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/add-user-details")
    public String addUserDetails(@Valid UserDetails userDetails,
                       BindingResult bindingResult,
                       Model model){
        userDetailsService.addUserDetails(userDetails);

        return "user_details_added_successfully";
    }
}
