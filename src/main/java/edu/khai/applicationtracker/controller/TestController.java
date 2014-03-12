package edu.khai.applicationtracker.controller;

import javax.validation.Valid;

import edu.khai.applicationtracker.model.TestModel;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TestController {


	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView getTest() {
		TestModel testModel = new TestModel();
		testModel.setTestField("first Call");

		return new ModelAndView("test", "data", testModel);
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public ModelAndView getPost(@Valid TestModel testModel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()){
			return new ModelAndView("test", "error", bindingResult.getAllErrors());
		}
		return new ModelAndView("test", "data", testModel);
	}

}