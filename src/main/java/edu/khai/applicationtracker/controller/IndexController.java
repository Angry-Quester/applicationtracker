package edu.khai.applicationtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {
	private static final String WELCOME_MESSAGE = "Welcome aboard!";
	private static final String WELCOME_MESSAGE_ANONIMOUS = "Welcome ANONIMOUS user!";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getContextIndex() {
			return new ModelAndView("index", "welcomeMessage", WELCOME_MESSAGE);
	}

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView getIndexPage() {

		return new ModelAndView("index", "welcomeMessage", WELCOME_MESSAGE_ANONIMOUS);
	}
}