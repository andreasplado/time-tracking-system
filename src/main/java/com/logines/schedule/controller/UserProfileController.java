package com.logines.schedule.controller;

import com.logines.schedule.model.Class;
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
import java.security.Principal;

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
            model.addAttribute("error", "Please check all data.");
            return "add_user_profile";
        } else {
            userProfileService.addUserProfile(userProfile);
            return "user_details_added_successfully";
        }
    }

    @GetMapping("/add-user-profile")
    public String edit(Model model) {
        model.addAttribute("userProfileForm", new UserProfile());

        return "add_user_profile";
    }

    @PostMapping("delete-user-profile/{username}")
    public String deleteClass(Model model, @PathVariable("username") String username,
                              @Valid Class aClass) {
        userProfileService.deleteByUsername(username);

        return "class_deleted_successfully";

    }
}
