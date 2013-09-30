package edu.khai.appticationtracker.controller;

import edu.khai.appticationtracker.model.Application;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplicationController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getApplication() {
    	Application application = new Application();
    		application.setApplicationId(new Long(12345));
        return new ModelAndView("index", "application", application);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView  postApptication(@ModelAttribute("index") Application application, BindingResult result) {
        return new ModelAndView("main","user", application);
    }
}