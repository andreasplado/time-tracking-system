package com.logines.schedule.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("error_title", "Oops! Page not found.");
                model.addAttribute("error_content", "Sorry this page was not found");
                model.addAttribute("error_nr", status);

                return "server_error";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("error_title", "Oops! Page error.");
                model.addAttribute("error_content", "We are sorry, but the page have some technical issues");
                model.addAttribute("error_nr", status);
                return "server_error";
            }
            model.addAttribute("error", status);
        }
        return "error_page";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
