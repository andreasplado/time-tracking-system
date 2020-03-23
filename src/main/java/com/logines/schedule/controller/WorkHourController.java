package com.logines.schedule.controller;

import com.logines.schedule.model.UserProfile;
import com.logines.schedule.model.WorkHour;
import com.logines.schedule.service.UserProfileService;
import com.logines.schedule.service.WorkHourService;
import com.logines.schedule.validator.WorkHourValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class WorkHourController {
    @Autowired
    private WorkHourService workHourService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private WorkHourValidator workHourValidator;

    @InitBinder("work_hour")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(workHourValidator);
    }

    @PostMapping("/add-work-hour")
    public String addWorkHour(@RequestBody @Valid @ModelAttribute WorkHour workHour,
                              BindingResult bindingResult,
                              Model model, String error, Principal principal,
                              final RedirectAttributes redirectAttributes){
        workHourValidator.validate(workHour, bindingResult);
        if(bindingResult.hasErrors()) {
            UserProfile userProfile = userProfileService.findUserProfile(principal.getName());
            if(userProfile != null){
                redirectAttributes.addFlashAttribute("error", bindingResult);
                model.addAttribute("userProfile", userProfile);
            }
            return "main";
        }

        model.addAttribute("message", "Workhour added successfully...");
        workHourService.addWorkHour(workHour);
        return "successful_page";
    }
}
