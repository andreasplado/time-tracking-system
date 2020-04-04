package com.logines.schedule.controller;

import com.logines.schedule.model.UserProfile;
import com.logines.schedule.model.Users;
import com.logines.schedule.model.WorkHour;
import com.logines.schedule.service.UserProfileService;
import com.logines.schedule.service.UserService;
import com.logines.schedule.service.WorkHourService;
import com.logines.schedule.validator.WorkHourValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Arrays;
import java.util.List;

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
        model.addAttribute("workHourForm", new WorkHour());
        workHourValidator.validate(workHour, bindingResult);
        model.addAttribute("message", "Workhour edited successfully...");




        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors());
            return "error_page";
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
        model.addAttribute("workHour", workHourService.viewWorkHour(id));

        return "edit_work_hour";
    }

    @PostMapping("/edit-work-hour/{id}")
    public String postWorkHour(@PathVariable("id") long id,
                       @Valid WorkHour workHour,
                       BindingResult bindingResult,
                       Model model) {
        workHourValidator.validate(workHour, bindingResult);
        if (bindingResult.hasErrors()) {
            return "error_page";
        } else {
            workHourService.updateWorkHour(workHour);
            model.addAttribute("message", "Workhour edited successfully...");
            return "successful_page";
        }
    }


    @GetMapping("/search-users-work-hour/{username}")
    public String searchWorkHourByUsername(@PathVariable("username") String username, Model model, Principal principal) {
        if(principal != null){
            model.addAttribute("usernameText", principal.getName());
        }
        List<WorkHour> workHourList = workHourService.findByUsernameReversed(username);
        model.addAttribute("workHours", workHourList);
        return "work_hour_username";
    }
}
