package edu.khai.applicationtracker.controller;

import java.beans.PropertyEditor;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import edu.khai.applicationtracker.model.AppUserPrincipal;
import edu.khai.applicationtracker.model.Application;
import edu.khai.applicationtracker.model.ApplicationC;
import edu.khai.applicationtracker.model.ApplicationType;
import edu.khai.applicationtracker.service.AppUserService;
import edu.khai.applicationtracker.service.ApplicationService;
import edu.khai.applicationtracker.service.ApplicationTypeService;


@Controller
@SessionAttributes("application")
public class ApplicationController {

    @Autowired
    private ApplicationTypeService applicationTypeService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private AppUserService appUserService;



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
    public String newApplicationsForm(Model model) {

        List<ApplicationType> applicationTypes = applicationTypeService.getApplicationTypes();

        model.addAttribute("applicationTypes", applicationTypes);

        return "applications/new";
    }

    @RequestMapping(value = "/applications/new/{applicationTypeId}", method = RequestMethod.GET)
    public String newApplicationForm(Model model,
                                    @PathVariable("applicationTypeId") Long applicationTypeId) {

        //get an applicationType to put it into application
        ApplicationType applicationType = applicationTypeService.getApplicationType(applicationTypeId);

        Application application = new Application();
                    application.setApplicationType(applicationType);

        String viewName = "";
        switch (applicationTypeId.toString()) {
        case "1":
            viewName = "applications/new/f1";
                application = new ApplicationC();
                model.addAttribute("application", application);
            break;
        case "2":
            viewName = "applications/new/f1a";
                model.addAttribute("application", application);
            break;
        }

        return viewName;
    }

    @RequestMapping(value = "/applications", method = RequestMethod.POST)
    public String newApplication(Model model, Application application, BindingResult errors, SessionStatus sessionStatus) {


        for (String key : model.asMap().keySet()){
            System.out.printf("------------- key :: %s --- value %s %n ",key, model.asMap().get(key));
        }

        ApplicationC c = (ApplicationC)model.asMap().get("application");

        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "applications/new/f1";
        }

        //get application entity after it was created
        //Application newApplication =
        applicationService.addApplication(application);

        //this line of code perfoms cleanup for the current session
        //An application attribute of the session to be exact
        sessionStatus.setComplete();



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
