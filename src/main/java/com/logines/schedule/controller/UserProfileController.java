package com.logines.schedule.controller;

import com.logines.schedule.model.UserProfile;
import com.logines.schedule.service.UserProfileService;
import com.logines.schedule.validator.UserProfileValidator;
import com.logines.schedule.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserProfileValidator userValidator;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(userValidator);
    }

    @RequestMapping(value = "/add-user-details", method = RequestMethod.POST, headers = "Content-type=application/*")
    public String addUserDetails(@Valid UserProfile userProfile,
                                 BindingResult bindingResult,
                                 Model model, String error) {
        userValidator.validate(userProfile, bindingResult);

        if(bindingResult.hasErrors()){
            model.addAttribute("error", "Please check all data.");
            return "add_user_details";
        }else{
            userProfileService.addUserProfile(userProfile);
            return "user_details_added_successfully";
        }

    }
}
