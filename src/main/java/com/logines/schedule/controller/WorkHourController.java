package com.logines.schedule.controller;

import com.logines.schedule.model.Job;
import com.logines.schedule.model.UserProfile;
import com.logines.schedule.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class WorkHourController {
    @Autowired
    private JobService jobService;

    @PostMapping("/add-work-hour")
    public String addWorkHour(@Valid Job job,
                                 BindingResult bindingResult,
                                 Model model){
        jobService.addJob(job);

        return "workhour_added_successfully";
    }
}
