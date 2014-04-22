package edu.khai.applicationtracker.controller;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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


	@InitBinder
	public void bindingTrimmer(WebDataBinder binder) {
		 PropertyEditor pe = new StringTrimmerEditor(false);

		 binder.registerCustomEditor(String.class, pe);
	}


/*==========================VIEW methods*/
	@RequestMapping(value = "/applications", method = RequestMethod.GET)
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

	@RequestMapping(value = "/applications/{applicationId}", method = RequestMethod.GET)
	public String getApplication(Model model,
									 @PathVariable Long applicationId) {
		//grab application if the user have an apropriate autority (ACL check)
		Application application = applicationService.getApplication(applicationId);

		//give it ot the user
		model.addAttribute("application", application);

		return "application";
	}


/*==========================Add Edit Delete methodds*/
	/*==========================Add methodds*/
	@RequestMapping(value = "/applications/new", method = RequestMethod.GET)
	public String newApplicationForm(Model model) {

		Application application = new Application();

		model.addAttribute("application", application);

		return "applications/new";
	}

	@RequestMapping(value = "/applications", method = RequestMethod.POST)
	public String newApplication(Model model, Application application, BindingResult errors) {

		if (errors.hasErrors()) {
			model.addAttribute("errors", errors);
            return "applications/new";
        }

		applicationService.addApplication(application);

        return "redirect:applications";
	}

	/*==========================Edit methodds*/
	@RequestMapping(value = "/applications/{applicationId}/edit", method = RequestMethod.GET)
	public String editApplicationForm(Model model,
									@PathVariable("applicationId") Long applicationId) {

		Application application = applicationService.getApplication(applicationId);

		model.addAttribute("application", application);

		return "applications/edit";
	}

	@RequestMapping(value = "/applications/{applicationId}", method = RequestMethod.POST)
	public String editApplication(Model model,
									@PathVariable("applicationId") Long applicationId,
									Application application,
									BindingResult errors) {
		if (errors.hasErrors()) {
			model.addAttribute("errors", errors);
            return "applications/edit";
        }

		application.setApplicationId(applicationId);
		applicationService.updateApplication(application);

        return "redirect:/applications";
	}

	/*==========================Delete methods*/
	@RequestMapping(value = "/applications/{applicationId}", method = RequestMethod.DELETE)
	public String deleteApplication(Model model,
									@PathVariable("applicationId") Long applicationId) {

		applicationService.removeApplication(applicationId);

        return "redirect:/applications";
	}






/*==========================File methodds*/
	/*==========================Just a stub for a while*/
	@RequestMapping(value = "/applications/{applicationId}/txt", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void getApplicationTxtById(Model model,
			@PathVariable Long applicationId,
			HttpServletResponse response) {
		//grab application if the user have an apropriate autority (ACL check)
		Application application = applicationService.getApplication(applicationId);

		try {
			response.setContentType("application/txt");
			response.addHeader("Content-Disposition", "; filename=" + "application.txt");
			response.addHeader("Content-Length", String.valueOf(application.toString().getBytes("UTF-8").length));

			PrintWriter pw = response.getWriter();
			pw.print(application.toString());

		} catch (Exception ioe) {

		}

	}


}
