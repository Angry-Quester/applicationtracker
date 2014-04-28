package edu.khai.applicationtracker.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
    private static final String LOGIN_FAILED_MESSAGE = "Login failed";
    private static final String LOGOUT_MESSAGE = "You've been logged out successfully";


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(Model model,
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        if (error != null) {
            model.addAttribute("error", LOGIN_FAILED_MESSAGE);
        }
        if (logout != null) {
            model.addAttribute("logout", LOGOUT_MESSAGE);
        }

        return "login";
    }


    @RequestMapping(value = "/loginTest", method = RequestMethod.GET)
    public ModelAndView getLoginTest() {

        return new ModelAndView("loginTest", "loginData", SecurityContextHolder.getContext().getAuthentication());
    }
}