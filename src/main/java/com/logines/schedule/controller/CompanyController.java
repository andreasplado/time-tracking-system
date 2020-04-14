package com.logines.schedule.controller;

import com.logines.schedule.model.Company;
import com.logines.schedule.model.Users;
import com.logines.schedule.model.WorkHour;
import com.logines.schedule.service.CompanyService;
import com.logines.schedule.service.UserService;
import com.logines.schedule.utils.CustomStringUtils;
import com.logines.schedule.validator.WorkHourValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private WorkHourValidator workHourValidator;

    @Autowired
    private UserService userService;

    @InitBinder("work_hour")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(workHourValidator);
    }

    @PostMapping("/add-company")
    public String addWorkHour(@RequestBody @Valid @ModelAttribute Company company,
                              BindingResult bindingResult,
                              Model model, String error, Principal principal) {
        model.addAttribute("companyForm", new Company());
        model.addAttribute("message", "Workhour edited successfully...");

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors());
            return "error_page";
        } else {
            model.addAttribute("message", "Company added successfully...");
            companyService.save(company);
            return "successful_page";
        }
    }

    @GetMapping("/edit-company/{id}")
    public String viewCompany(Model model, @PathVariable("id") int id, Principal principal) {
        model.addAttribute("workHourEditForm", new WorkHour());

        if(principal != null){
            Users users = userService.findByUsername(principal.getName());
            Company company = companyService.findById(id);
            model.addAttribute("usernameText", principal.getName());
            model.addAttribute("role", users.getRole());
        }
        Company workHour = companyService.findById(id);
        model.addAttribute("company", companyService.findById(id));

        return "edit_company";
    }

    @PostMapping("/edit-company/{id}")
    public String editCompany(@PathVariable("id") long id,
                       @Valid Company company,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            return "error_page";
        } else {
            companyService.save(company);
            model.addAttribute("message", "Workhour edited successfully...");
            return "successful_page";
        }
    }


    @GetMapping("/search-company/{companyName}")
    public String searchCompanyByCompanyName(@PathVariable("companyName") String companyName, Model model, Principal principal) {
        if(principal != null){
            model.addAttribute("usernameText", principal.getName());
        }
        model.addAttribute("searchString", companyName);
        List<Company> companyList = companyService.findByCompanyName(companyName);
        model.addAttribute("companies", companyList);
        return "search_users_work_hour";
    }

    @PostMapping("delete-company/{id}")
    public String deleteCompany(Model model, @PathVariable("id") int id) {
        if (companyService.deleteCompany(id)) {
            model.addAttribute("message", "Workhour deleted successfully...");
            return "successful_page";
        } else {
            model.addAttribute("message", "Workhour not found...");
            return "successful_page";
        }
    }

}
