package edu.khai.applicationtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.model.Application;
import edu.khai.applicationtracker.service.AppUserManager;
import edu.khai.applicationtracker.service.ApplicationManager;


@Controller
public class AppUserController {

	@Autowired
	private AppUserManager appUserManager;

	@Autowired
	private ApplicationManager applicationManager;


	@RequestMapping(value = "/appUsers", method = RequestMethod.GET)
	public String getAppUsers(Model model) {
		List<AppUser> appUsers = appUserManager.getAppUsers();
			model.addAttribute("applications", appUsers);
		return "appUsers";
	}

	@RequestMapping(value = "/appUsers/{appUserId}", method = RequestMethod.GET)
	public String getAppUserById(@PathVariable Long appUserId, Model model) {
		AppUser appUser= appUserManager.getAppUser(appUserId);
			model.addAttribute("appUser", appUser);
		return "appUser";
	}

}