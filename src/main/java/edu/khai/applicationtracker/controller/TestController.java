package edu.khai.applicationtracker.controller;

import java.util.Map;

import javax.validation.Valid;

import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.model.AppUserPrincipal;
import edu.khai.applicationtracker.model.TestModel;
import edu.khai.applicationtracker.service.AppUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TestController {

    @Autowired
    private AppUserService appUserManager;

    /**
     * @return the appUserManager
     */
    public AppUserService getAppUserManager() {
        return appUserManager;
    }

    /**
     * @param appUserManager the appUserManager to set
     */
    public void setAppUserManager(AppUserService appUserManager) {
        this.appUserManager = appUserManager;
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTest(Model testModel) {
        AppUserPrincipal aup =
                (AppUserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //эти данные можно будет получить на jsp странице
        //по типу переданных данных т.е. так: ${string}
        testModel.addAttribute("smartModelContent");

        //эти данные можно будет получить на jsp странице
        //по типу переданных данных т.е. так: ${aup}
        //
        //org.springframework.core.Conventions
        //
        //Determine the conventional variable name for
        //the supplied Object based on its concrete type.
        //The convention used is to return the uncapitalized short name of the Class,
        //according to JavaBeans property naming rules:
        //So,
        //com.myapp.Product becomes product;
        //com.myapp.MyProduct becomes myProduct;
        //com.myapp.UKProduct becomes UKProduct.

        testModel.addAttribute(aup);

        //эти данные можно будет получить на jsp странице явным образом
        //по имени т.е. так: ${data}
        testModel.addAttribute("data", aup);


        return "test";
    }

    @RequestMapping(value = "/test/{someData}", method = RequestMethod.GET)
    public ModelAndView getTest(@PathVariable String someData) {
        return new ModelAndView("test", "data", someData);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ModelAndView getPost(
                                @Valid TestModel testModel, BindingResult bindingResult,
                                @RequestParam(value="id", required=false) Long appUserId,
                                ModelMap modelMap) {

        ModelAndView mav = new ModelAndView("test");

        mav.addObject("modelMap", modelMap);
           mav.addObject("data", testModel);
           mav.addObject("errors", bindingResult);

        if (bindingResult.hasErrors()) {
            mav.addObject("errors", bindingResult);
            return mav;
        }

        return mav;
    }

}