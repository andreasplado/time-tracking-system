package com.logines.schedule.controller;

import com.logines.schedule.model.UserProfile;
import com.logines.schedule.service.UserProfileService;
import com.logines.schedule.validator.UserProfileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserProfileValidator userProfileValidator;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(userProfileValidator);
    }

    @RequestMapping(value = "/add-user-profile", method = RequestMethod.POST, headers = "Content-type=application/*")
    public String addUserDetails(Model model, @RequestBody @Valid @ModelAttribute UserProfile userProfile,
                                 BindingResult bindingResult,
                                 String error) {
        userProfileValidator.validate(userProfile, bindingResult);


        if (bindingResult.hasErrors()) {
            return "add_user_profile";
        } else {
            userProfileService.addUserProfile(userProfile);
            model.addAttribute("message", "User details added successfully...");
            return "successful_page";
        }
    }

    @GetMapping("/add-user-profile")
    public String edit(Model model, String error) {
        model.addAttribute("userProfileForm", new UserProfile());
        if (error != null)
            model.addAttribute("error", "There is a problem with adding user profile. Please check all fields!");

        return "add_user_profile";
    }

    @PostMapping("delete-user-profile/{username}")
    public String deleteClass(Model model, @PathVariable("username") String username) {
        userProfileService.deleteByUsername(username);
        model.addAttribute("message", "Class deleted successfully...");

        return "successful_page";

    }
}
