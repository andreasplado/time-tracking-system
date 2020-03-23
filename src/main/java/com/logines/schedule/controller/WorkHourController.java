package com.logines.schedule.controller;

import com.logines.schedule.model.WorkHour;
import com.logines.schedule.service.WorkHourService;
import com.logines.schedule.validator.WorkHourValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class WorkHourController {
    @Autowired
    private WorkHourService workHourService;

    @Autowired
    private WorkHourValidator workHourValidator;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(workHourValidator);
    }

    @PostMapping("/add-work-hour")
    public String addWorkHour(@Valid WorkHour workHour,
                                 BindingResult bindingResult,
                                 Model model, String error){
        workHourValidator.validate(workHour, bindingResult);
        if(bindingResult.hasErrors()) {
            model.addAttribute("workhouraddingError", "Form posting failed.");
            return "workhour_adding_error";
        }
        model.addAttribute("message", "Workhour added successfully...");
        workHourService.addWorkHour(workHour);
        return "successful_page";
    }
}
