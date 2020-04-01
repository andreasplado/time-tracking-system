package com.logines.schedule.controller;

import com.logines.schedule.model.Users;
import com.logines.schedule.model.WorkHour;
import com.logines.schedule.model.UserProfile;
import com.logines.schedule.service.UserDetailsServiceImpl;
import com.logines.schedule.service.UserProfileService;
import com.logines.schedule.service.UserService;
import com.logines.schedule.service.WorkHourService;
import com.logines.schedule.validator.UserValidator;
import com.logines.schedule.validator.WorkHourValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
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
    private WorkHourService workHourService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private WorkHourValidator workHourValidator;
    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @InitBinder("work_hour")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(workHourValidator);
    }

    //@RequestMapping(value="/",method = RequestMethod.GET)
    @GetMapping({"/", "/home"})
    public String welcome(Model model, Principal principal, String error) {
        model.addAttribute("workHourForm", new WorkHour());
        if (principal != null) {
            model.addAttribute("usernameText", principal.getName());
            UserProfile userProfile = userProfileService.findUserProfile(principal.getName());
            Users users = userService.findByUsername(principal.getName());
            //Kui kasutajaandmeid on lisatud
            if (userProfile != null) {
                List<WorkHour> allWorkhours = workHourService.getAllWorkHours();
                Collections.reverse(allWorkhours);
                List<WorkHour> userWorkHours = workHourService.findByUsername(principal.getName());
                model.addAttribute("userProfile", userProfile);
                model.addAttribute("userWorkHours", userWorkHours);
                model.addAttribute("allWorkHours", allWorkhours);
                model.addAttribute("role", users.getRole());

                return "main";
            } else {
                model.addAttribute("userProfileForm", new UserProfile());
                if (error != null)
                    model.addAttribute("error", "Your username and password is invalid.");
                return "add_user_profile";
            }
        }else{
            return "user-login";
        }
    }

    @PostMapping("time/{id}")
    public String postTime(@PathVariable("id") long id,
                           @Valid WorkHour workHour,
                           BindingResult bindingResult,
                           Model model) {
        workHourService.addWorkHour(workHour);
        model.addAttribute("message", "Workhour edited successfully...");

        return "successful_page";
    }

    @GetMapping("/work-hour-details/{id}")
    public String classDetails(Model model, @PathVariable("id") int id) {
        WorkHour workHour = workHourService.viewWorkHour(id);
        if (workHour != null) {
            model.addAttribute("workHour", workHour);
            return "work_hour_details";
        } else {
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
    public String deleteWorkHour(Model model, @PathVariable("id") int id) {
        if (workHourService.deleteJob(id)) {
            model.addAttribute("message", "Workhour deleted successfully...");
            return "successful_page";
        } else {
            model.addAttribute("message", "Workhour not found...");
            return "successful_page";
        }
    }
}
