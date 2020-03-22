package com.logines.schedule.controller;

import com.logines.schedule.model.WorkHour;
import com.logines.schedule.service.WorkHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class WorkHourController {
    @Autowired
    private WorkHourService workHourService;

    @PostMapping("/add-work-hour")
    public String addWorkHour(@Valid WorkHour workHour,
                                 BindingResult bindingResult,
                                 Model model, String error){
        if(bindingResult.hasErrors()) {
            model.addAttribute("workhouraddingError", "Form posting failed.");
            return "workhour_adding_error";
        }
        model.addAttribute("message", "Workhour added successfully...");
        workHourService.addJob(workHour);
        return "successful_page";
    }
}
