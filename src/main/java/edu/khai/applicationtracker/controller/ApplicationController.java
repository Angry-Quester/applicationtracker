package edu.khai.applicationtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.model.Application;
import edu.khai.applicationtracker.service.AppUserService;
import edu.khai.applicationtracker.service.ApplicationService;


@Controller
public class ApplicationController {

	@Autowired
	private ApplicationService applicationManager;

	@Autowired
	private AppUserService appUserManager;

	@RequestMapping(value = "/applications", method = RequestMethod.GET)
	public String getApplications(Model model) {
		//getting authenticated user name
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//use the name to load applications
		AppUser au = appUserManager.getAppUserByName(auth.getName());

		List<Application> applications = applicationManager.getApplicationsByAppUserId(au.getAppUserId());

		model.addAttribute("applications", applications);

		return "applications";
	}

	@RequestMapping(value = "/applications/{applicationId}", method = RequestMethod.GET)
	public String getApplicationById(@PathVariable Long applicationId, Model model) {
		Application application = applicationManager.getApplication(applicationId);

		model.addAttribute("application", application);

		return "application";
	}
}