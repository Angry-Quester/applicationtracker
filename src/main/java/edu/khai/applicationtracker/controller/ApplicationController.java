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

	@RequestMapping(value = "/applications", method = RequestMethod.GET)
	public String getApplications(Model model) {
		//getting authenticated user ID
		AppUserPrincipal aup =
				(AppUserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		List<Application> applications =
				applicationService.getApplicationsByAppUserId(aup.getUserId());

		model.addAttribute("applications", applications);

		return "applications";
	}

	@RequestMapping(value = "/applications/{applicationId}", method = RequestMethod.GET)
	public String getApplicationById(@PathVariable Long applicationId, Model model) {
		/*
		 * Here i must check request validity
		 * Compare applicationId with AppUserPrincipal id
		 * and make descision abot further work
		 */

		/*
		 * PreProcessor checks input data
		 *
		 * Call method boolean preProcessor(applicationId, principalId)
		 *
		 * true - everything works as usual
		 * false - get en "error object" from PreProcessor
		 *  		publish the "error object" through the model
		 *
		 *
		 *
		 * PreProcessor.preProcessor("type", entityId, principalId) {
		 * 		type = application {
		 * 			applicationDAO.checkSecurity(entityId, principalId)
		 * 		}
		 * }
		 *
		 * applicationDAO.checkSecurity(entityId, principalId) {
		 *		currentSession.createQuery("CHECK QUERY")
		 *						.setParameter(entityId)
		 *						.setParameter(principalId)
		 *
		 * }
		 *
		 */

		//getting authenticated user ID

		AppUserPrincipal aup =
				(AppUserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		boolean securityMarker =
					applicationService.securityCheck(aup.getUserId(), applicationId);

		Application application=
				securityMarker ?
				applicationService.getApplication(applicationId) :
				null;

//		Application application = applicationService.getApplication(applicationId);

		model.addAttribute("application", application);

		return "application";
	}
}