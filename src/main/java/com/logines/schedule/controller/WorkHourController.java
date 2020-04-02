package com.logines.schedule.controller;

import com.logines.schedule.model.UserProfile;
import com.logines.schedule.model.Users;
import com.logines.schedule.model.WorkHour;
import com.logines.schedule.service.UserProfileService;
import com.logines.schedule.service.UserService;
import com.logines.schedule.service.WorkHourService;
import com.logines.schedule.validator.WorkHourValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.time.format.DateTimeFormatter;

@Controller
public class WorkHourController {
    @Autowired
    private WorkHourService workHourService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private WorkHourValidator workHourValidator;

    @Autowired
    private UserService userService;

    @InitBinder("work_hour")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(workHourValidator);
    }

    @PostMapping("/add-work-hour")
    public String addWorkHour(@RequestBody @Valid @ModelAttribute WorkHour workHour,
                              BindingResult bindingResult,
                              Model model, String error, Principal principal) {
        workHourValidator.validate(workHour, bindingResult);

        if (bindingResult.hasErrors()) {
            UserProfile userProfile = userProfileService.findUserProfile(principal.getName());
            if (userProfile != null) {
                model.addAttribute("userProfile", userProfile);
                return "main";
            } else {
                return "redirect:/";
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Something went wrong. Please check all credentials.");
            return "main";
        } else {
            model.addAttribute("message", "Workhour added successfully...");
            workHourService.addWorkHour(workHour);
            return "successful_page";
        }
    }

    @GetMapping("/edit-work-hour/{id}")
    public String viewEditWorkHour(Model model, @PathVariable("id") int id, Principal principal) {
        model.addAttribute("workHourEditForm", new WorkHour());

        if(principal != null){
            Users users = userService.findByUsername(principal.getName());
            model.addAttribute("usernameText", principal.getName());
            model.addAttribute("role", users.getRole());
        }
        WorkHour workHour = workHourService.viewWorkHour(id);
        String createdAt = workHour.getCreated_at();
        model.addAttribute("workHour", workHourService.viewWorkHour(id));
        model.addAttribute("createdAt", createdAt);

        return "edit_work_hour";
    }

    @PostMapping("/edit-work-hour/{id}")
    public String postWorkHour(@PathVariable("id") long id,
                       @Valid WorkHour workHour,
                       BindingResult bindingResult,
                       Model model) {
        workHourValidator.validate(workHour, bindingResult);
        if (bindingResult.hasErrors()) {
            return "work_hour_edit";
        } else {
            workHourService.updateWorkHour(workHour);
            model.addAttribute("message", "Workhour edited successfully...");
            return "successful_page";
        }
    }
}
