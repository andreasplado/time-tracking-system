package com.logines.schedule.controller;

import com.logines.schedule.model.Company;
import com.logines.schedule.model.Users;
import com.logines.schedule.model.WorkHour;
import com.logines.schedule.service.CompanyService;
import com.logines.schedule.service.UserService;
import com.logines.schedule.service.WorkHourService;
import com.logines.schedule.validator.UserValidator;
import com.logines.schedule.validator.WorkHourValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private WorkHourService workHourService;

    @Autowired
    private CompanyService companyService;
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

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Instant.class, null, new CustomDateEditor(dateFormat, true));
    }

    //@RequestMapping(value="/",method = RequestMethod.GET)
    @GetMapping({"/"})
    public String welcome(Model model, Principal principal, String error) {
        model.addAttribute("workHourForm", new WorkHour());
        model.addAttribute("companyForm", new Company());
        model.addAttribute("registerForm", new Users());

        if (principal != null) {
            model.addAttribute("usernameText", principal.getName());
            Users myUser = userService.findByUsername(principal.getName());

            //Kui kasutajaandmeid on lisatud

            List<WorkHour> allWorkhours = workHourService.getAllWorkHours();
            List<WorkHour> userWorkHours = workHourService.findByUsernameReversed(principal.getName());
            List<Company> companyList = companyService.findAll();
            if(myUser != null) {
                model.addAttribute("users", userService.findAll());
                model.addAttribute("userWorkHoursSum", workHourService.userWorkHoursSum(principal.getName()));
                model.addAttribute("userWorkHoursSumWithoutLunchtime", workHourService.totalUserWorkHour(principal.getName()));
                model.addAttribute("lunchHoursSum", workHourService.userTotalLunchHoursSum(principal.getName()));
                model.addAttribute("totalWorkHoursSum", workHourService.totalWorkHoursSum());
                model.addAttribute("myUser", myUser);
                model.addAttribute("userWorkHours", userWorkHours);
                model.addAttribute("allWorkHours", allWorkhours);
                model.addAttribute("companyList", companyList);
                model.addAttribute("role", myUser.getRole());
            } else{
                return "user-login";
            }
            return "main";
        } else {
            return "user-login";
        }
    }

    @GetMapping("/get-user/{id}")
    public String searchUserByUserId(@PathVariable("id") int id, Model model, Principal principal) {
        Users userForm = new Users();
        model.addAttribute("userForm", userForm);
        List<WorkHour> userWorkHours = null;
        List<Company> companyList = companyService.findAll();
        if(principal != null){
            model.addAttribute("usernameText", principal.getName());
            Users myUser = userService.findByUsername(principal.getName());
            Users users = userService.findByid(id);
            userWorkHours = workHourService.findByUsernameReversed(users.getUsername());
            model.addAttribute("user", users);
            model.addAttribute("role", myUser.getRole());
            model.addAttribute("userWorkHours", userWorkHours);
            model.addAttribute("companyList", companyList);
            model.addAttribute("userWorkHoursSum", workHourService.userWorkHoursSum(users.getUsername()));
            model.addAttribute("userLunchHoursSum", workHourService.userTotalLunchHoursSum(users.getUsername()));
            model.addAttribute("userTotalWorkHoursSum", workHourService.totalUserWorkHour(users.getUsername()));
            model.addAttribute("userWorkHoursSumWithoutLunch", workHourService.totalWorkHourRow(users.getUsername()));
            return "edit_user";
        }
        return "redirect:/login";
    }


    @PostMapping("time/{id}")
    public String postTime(@PathVariable("id") long id,
                           @Valid WorkHour workHour,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors());
            return "error_page";
        }
        workHourService.addWorkHour(workHour);
        model.addAttribute("message", "Workhour edited successfully...");

        return "successful_page";
    }

    @GetMapping("/work-hour-details/{id}")
    public String workHourDetails(Model model, @PathVariable("id") int id, Principal principal) {
        WorkHour workHour = workHourService.viewWorkHour(id);
        model.addAttribute("usernameText", principal.getName());
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
}
