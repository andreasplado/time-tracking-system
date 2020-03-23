package com.logines.schedule.controller;

import com.logines.schedule.model.WorkHour;
import com.logines.schedule.model.UserProfile;
import com.logines.schedule.service.WorkHourServiceImpl;
import com.logines.schedule.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private WorkHourServiceImpl workHourServiceImpl;
    @Autowired
    private AuthenticationManager authenticationManager;

    //@RequestMapping(value="/",method = RequestMethod.GET)
    @GetMapping({"/", "/home"})
    public String welcome(Model model, Principal principal, String error) {
        model.addAttribute("usernameText", principal.getName());

        UserProfile userProfile = userProfileService.findUserProfile(principal.getName());
        //Kui kasutajaandmeid on lisatud
        if(userProfile != null) {
            List<WorkHour> allWorkhours = workHourServiceImpl.getAllWorkHours();
            Collections.reverse(allWorkhours);
            List<WorkHour> userWorkHours = workHourServiceImpl.findByUsername(principal.getName());
            model.addAttribute("workHourForm", new WorkHour());
            model.addAttribute("userProfile", userProfile);
            model.addAttribute("userWorkHours", userWorkHours);
            model.addAttribute("allWorkHours", allWorkhours);
            return "main";
        }else{
            model.addAttribute("userProfileForm", new UserProfile());
            if (error != null)
                model.addAttribute("error", "Your username and password is invalid.");
            return "add_user_profile";
        }
    }

    @GetMapping("/edit-work-hour/{id}")
    public String edit(Model model, @PathVariable("id") int id){
        WorkHour workHour = workHourServiceImpl.viewWorkHour(id);
        String createdAt  = workHour.getCreated_at().format(DateTimeFormatter.ISO_DATE_TIME);
        model.addAttribute("workHour", workHourServiceImpl.viewWorkHour(id));
        model.addAttribute("createdAt", createdAt );

        return "work_hour_edit";
    }

    @PostMapping("/edit-work-hour/{id}")
    public String edit(@PathVariable("id") long id,
                       @Valid Class aClass,
                       BindingResult bindingResult,
                       Model model){
        model.addAttribute("message", "Workhour edited successfully...");

        return "successful_page";
    }

    @PostMapping("time/{id}")
    public String postTime(@PathVariable("id") long id,
                       @Valid WorkHour workHour,
                       BindingResult bindingResult,
                       Model model){
        workHourServiceImpl.addWorkHour(workHour);
        model.addAttribute("message", "Workhour edited successfully...");

        return "successful_page";
    }

    @GetMapping("/work-hour-details/{id}")
    public String classDetails(Model model, @PathVariable("id") int id){
        WorkHour workHour = workHourServiceImpl.viewWorkHour(id);
        if(workHour != null){
            model.addAttribute("scheduleClass", workHour);
            return "work_hour_details";
        }else {
            return "404";
        }
    }

    @GetMapping(value = "/images/{image}", produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody
    byte[] getImage(@PathVariable String image) throws IOException {
        ClassPathResource file = new ClassPathResource("static/images/" + image);
        byte[] bytes;
        bytes = StreamUtils.copyToByteArray(file.getInputStream());
        return bytes;
    }

    @PostMapping("delete-work-hour/{id}")
    public String deleteWorkHour(Model model, @PathVariable("id") int id){
        if(workHourServiceImpl.deleteJob(id)){
            model.addAttribute("message", "Workhour deleted successfully...");
            return "successful_page";
        }else{
            model.addAttribute("message", "Workhour not found...");
            return "successful_page";
        }
    }
}
