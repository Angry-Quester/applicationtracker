package edu.khai.applicationtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.model.AppUserPrincipal;
import edu.khai.applicationtracker.model.Application;
import edu.khai.applicationtracker.service.AppUserService;
import edu.khai.applicationtracker.service.ApplicationService;


@Controller
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private AppUserService appUserManager;

	@RequestMapping(value = "/applications",
					method = RequestMethod.GET)
	public String getApplications(Model model) {
		//get authenticated user ID to create available applications list
		AppUserPrincipal aup =
				(AppUserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		//get applications list
		List<Application> applications =
				applicationService.getApplicationsByAppUserId(aup.getUserId());

		//add applications into model
		model.addAttribute("applications", applications);

		return "applications";
	}

	@RequestMapping(value = "/applications/new",
					method = RequestMethod.GET)
	public String newApplication(Application application) {
		application = new Application();
		return "applications/new";
	}

	@RequestMapping(value = "/applications",
			method = RequestMethod.POST)
	public String newApplicationAdd(Model model, Application application, BindingResult errors) {
		if (errors.hasErrors()) {
			model.addAttribute("errors", errors);
            return "applications/new";
        }

		applicationService.addApplication(application);

        return "redirect:applications";
	}


	@RequestMapping(value = "/applications/{applicationId}",
					method = RequestMethod.GET)
	public String getApplicationById(Model model,
									 @PathVariable Long applicationId) {
		//grab application if the user have an apropriate autority (ACL check)
		Application application = applicationService.getApplication(applicationId);

		//give it ot the user
		model.addAttribute("application", application);

		return "application";
	}


}