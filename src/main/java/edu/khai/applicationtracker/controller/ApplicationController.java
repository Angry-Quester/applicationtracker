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

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import edu.khai.applicationtracker.model.AppUserPrincipal;
import edu.khai.applicationtracker.model.Application;
import edu.khai.applicationtracker.model.ApplicationC;
import edu.khai.applicationtracker.model.ApplicationType;
import edu.khai.applicationtracker.model.application.ApplicationForm1;
import edu.khai.applicationtracker.model.application.ApplicationForm11;
import edu.khai.applicationtracker.model.application.ApplicationForm12;
import edu.khai.applicationtracker.model.application.ApplicationForm13;
import edu.khai.applicationtracker.model.application.ApplicationForm14;
import edu.khai.applicationtracker.service.AppUserService;
import edu.khai.applicationtracker.service.ApplicationService;
import edu.khai.applicationtracker.service.ApplicationTypeService;


/**
 * @author Quester
 *
 */
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
    /**
     * Returns All Application to the appropriate user
     *
     * @param model srores applications attribute
     * @return "applications" view
     */
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

    /** Returns single application data by it's id
     *
     * @param model
     * @param applicationId
     * @return
     */
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
    /**
     * Shows a user all possible types of applications
     *
     * @param model contains all available application types
     * @return "applications/new" view
     */
    @RequestMapping(value = "/applications/new", method = RequestMethod.GET)
    public String newApplicationsForm(Model model) {

        List<ApplicationType> applicationTypes = applicationTypeService.getApplicationTypes();

        model.addAttribute("applicationTypes", applicationTypes);

        return "applications/new";
    }

    /**
     * Shows a user "New application" form and populates application model with data
     *
     * @param model contains application entity according to the type
     * @return ""applications/new/" + applicationTypeId" view
     */
    @RequestMapping(value = "/applications/new/{applicationTypeId}", method = RequestMethod.GET)
    public String newApplicationForm(Model model,
                                    @PathVariable("applicationTypeId") Long applicationTypeId) {

        //Choose a class from the applications list
        Class<? extends Application> applicationClass = applicationClassSwitchBoard(applicationTypeId);

        //get an applicationType to put it into application and compile fresh application
        ApplicationType applicationType = applicationTypeService.getApplicationType(applicationTypeId);

        //get an application instance to use it in the conversation and put it inside the model
        Application application = applicationFactory(applicationClass, applicationType);
        model.addAttribute("application", application);

        return "applications/new/" + applicationTypeId;
    }


    /**
     * Saves a new application
     *
     * @param model         - I keep it here for no purpose
     * @param application   - application to bind data to
     * @param errors        - binding errors
     * @param sessionStatus - need it here to clear the seesion after an application was saved
     * @return the same form if there were errors, ("applications/new/" + application.getApplicationType().getApplicationTypeId())
     *          or "redirect:applications"
     */
    @RequestMapping(value = "/applications", method = RequestMethod.POST)
    public String newApplication(Model model, @Valid Application application, BindingResult errors, SessionStatus sessionStatus) {

        //Somehow databinding is polymorthic so there is nothing to worry about
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            //return the a user to the form where an error occured
            return "applications/new/" + application.getApplicationType().getApplicationTypeId();
        }

        //get application entity after it was created
        //Application newApplication =
        applicationService.addApplication(application);

        //this line of code perfoms cleanup for the current session
        //ob an application attribute of the session to be exact
        sessionStatus.setComplete();

        return "redirect:applications";
    }

    /*==========================Edit methodds*/
    /**
     * Shows a user "Edir application" form
     *
     * @param model contains application entity according to the type
     * @return (""applications/edit/" + applicationType.getApplicationTypeId()") view
     */
    @RequestMapping(value = "/applications/{applicationId}/edit", method = RequestMethod.GET)
    public String editApplicationForm(Model model,
                                    @PathVariable("applicationId") Long applicationId) {

        Application application = applicationService.getApplication(applicationId);

        ApplicationType applicationType = application.getApplicationType();

        model.addAttribute("application", application);

        return "applications/edit/" + applicationType.getApplicationTypeId();
    }


    /**
     * Saves an application after editing stops
     *
     * @param model         - I keep it here for no purpose
     * @param applicationId - have to check this value to give back a user correct form in case of an error
     * @param application   - application to bind data to
     * @param errors        - binding errors
     * @return the same form if there were errors, ("applications/new/" + application.getApplicationType().getApplicationTypeId())
     *          or "redirect:applications"
     */
    @RequestMapping(value = "/applications/{applicationId}", method = RequestMethod.POST)
    public String editApplication(Model model,
                                    @PathVariable("applicationId") Long applicationId,
                                    Application application,
                                    BindingResult errors) {
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "applications/edit/" + application.getApplicationType().getApplicationTypeId();
        }

        application.setApplicationId(applicationId);
        applicationService.updateApplication(application);

        return "redirect:/applications";
    }

    /*==========================Delete methods*/
    /**
     * Deletes an application by it's id
     * Nothing fancy here
     *
     * @param model         -
     * @param applicationId - id of the application to delete
     * @return "redirect:/applications"
     */
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

    /**
     * This contraption creates Application instances according to the class given to it
     * and populates it with the given application type
     *
     * @param clazz - application instance type
     * @param applicationType - application instance type to use inside this application
     * @return application instance. If it's impossible to create such an instance - returns null
     */
    @SuppressWarnings("unchecked")
    private <T extends Application> T applicationFactory(Class<T> clazz, ApplicationType applicationType) {
        try {
            T application = (T)Class.forName(clazz.getCanonicalName()).newInstance();
                application.setApplicationType(applicationType);
            return application;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    /**
     * Very crude implementation.
     * Some sort of an application classes switchboard.
     * It is intended as a temporary substitude for a better solution i can't think of right now.
     * I must find the way to implement it in a better way
     *
     * @param applicationTypeId
     * @return - application class according to the applicationTypeId. If no class found returns null
     */
    private Class<? extends Application> applicationClassSwitchBoard(Long applicationTypeId) {
        switch (applicationTypeId.intValue()) {
            case 1: return ApplicationForm1.class;
            case 2: return ApplicationForm11.class;
            case 3: return ApplicationForm12.class;
            case 4: return ApplicationForm13.class;
            case 5: return ApplicationForm14.class;
            default : return null;
        }
    }
}
