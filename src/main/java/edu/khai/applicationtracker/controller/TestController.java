package edu.khai.applicationtracker.controller;

import javax.validation.Valid;

import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.model.TestModel;
import edu.khai.applicationtracker.service.AppUserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TestController {

	@Autowired
	private AppUserManager appUserManager;

	/**
	 * @return the appUserManager
	 */
	public AppUserManager getAppUserManager() {
		return appUserManager;
	}

	/**
	 * @param appUserManager the appUserManager to set
	 */
	public void setAppUserManager(AppUserManager appUserManager) {
		this.appUserManager = appUserManager;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView getTest() {
		/*
		TestModel testModel = new TestModel();
		testModel.setTestField("first Call");
		*/
		AppUser appUser = appUserManager.getAppUser(new Long(5));
		return new ModelAndView("test", "data", appUser);
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public ModelAndView getPost(
			@Valid TestModel testModel, BindingResult bindingResult,
			@RequestParam("id") Long appUserId,
			ModelMap modelMap
			) {

        ModelAndView mav = new ModelAndView("test");

        mav.addObject("modelMap", modelMap);
       	mav.addObject("data", testModel);
       		AppUser appUser = appUserManager.getAppUser(appUserId);
       			appUser.setUsername(testModel.getTestField());
       				appUserManager.saveAppUser(appUser);
       	mav.addObject("info", appUser);
       	mav.addObject("errors", bindingResult);

		if (bindingResult.hasErrors()) {
			mav.addObject("errors", bindingResult);
			return mav;
		}

		return mav;
	}

}