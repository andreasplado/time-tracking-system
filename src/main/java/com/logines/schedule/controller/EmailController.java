package com.logines.schedule.controller;

import com.logines.schedule.service.CompanyService;
import com.logines.schedule.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/login/{qrscanned}")
    public String sendEmail(@PathVariable("qrscanned") String qrscanned, Model model) throws Exception {
        emailService.sendEmail();
        model.addAttribute("qrscanned", qrscanned);
        return "user_login";
    }
}