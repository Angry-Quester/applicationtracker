package edu.khai.applicationtracker.controller;

import edu.khai.applicationtracker.model.Application;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ApplicationController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getApplication() {
    	Application application = new Application();
    		application.setApplicationId(new Long(1));

        return new ModelAndView("index", "application", application);
    }

	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLogin() {

        return new ModelAndView("login", "loginData", SecurityContextHolder.getContext().getAuthentication());
    }

	@RequestMapping(value = "/login/1", method = RequestMethod.GET)
    public ModelAndView getLogin1() {
        return new ModelAndView("login", "test", "test");
    }

}