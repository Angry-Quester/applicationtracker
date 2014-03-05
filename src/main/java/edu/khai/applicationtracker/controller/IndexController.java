package edu.khai.applicationtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/")
public class IndexController {
	private static final String WELCOME_MESSAGE = "Welcome aboard!";

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView getApplication() {

		return new ModelAndView("index", "welcomeMessage", WELCOME_MESSAGE);
	}
}