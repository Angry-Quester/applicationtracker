package edu.khai.applicationtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.khai.applicationtracker.model.ApplicationType;
import edu.khai.applicationtracker.service.ApplicationTypeService;



/**
 * @author Quester
 *
 */
@Controller
public class ApplicationTypeController {

    @Autowired
    private ApplicationTypeService applicationTypeService;


/*==========================VIEW methods*/


    /**
     * Returns a list of all available application types in JSON format
     * @param model
     * @return List<ApplicationType>
     */
    @RequestMapping(value = "/applicationtypes", method = RequestMethod.GET)
    @ResponseBody
    public List<ApplicationType> getApplications(Model model) {

        return applicationTypeService.getApplicationTypes();
    }

}
