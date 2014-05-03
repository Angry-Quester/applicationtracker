package edu.khai.applicationtracker.controller;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.model.AppUserPrincipal;
import edu.khai.applicationtracker.model.TestModel;
import edu.khai.applicationtracker.service.AppUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes({"testModel", "testModelsList"})
public class TestController {

    @InitBinder
    public void bindingTrimmer(WebDataBinder binder) {
        PropertyEditorSupport pe = new PropertyEditorSupport() {
            @Override
            public void setAsText(String testControllerIntegerValue) throws IllegalArgumentException {
                Long longField = Long.parseLong(testControllerIntegerValue);
                TestModel tm = new TestModel();
                          tm.setTestIntegerField(longField);
                          tm.setTestField("master" + String.valueOf(longField) + "@mail.com");
                setValue(tm);
            }
        };

         binder.registerCustomEditor(TestModel.class, "testModel", pe);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView getTest(Model model1) {
        //эти данные можно будет получить на jsp странице
        //по типу переданных данных т.е. так: ${string}
        //testModel.addAttribute("smartModelContent");

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

        //testModel.addAttribute(aup);

        //эти данные можно будет получить на jsp странице явным образом
        //по имени т.е. так: ${data}

        //AppUserPrincipal aup = (AppUserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ModelAndView mav = new ModelAndView();

        //mav.addObject("data", aup);

        TestModel testModel = new TestModel();
                  testModel.setTestIntegerField(Long.valueOf(1234567890));
                  testModel.setTestField("crash@meme.to");

                  TestModel l4 = new TestModel();
                  l4.setTestIntegerField(Long.valueOf(444));
                  l4.setTestField("w444w@meme.to");

                  TestModel l5 = new TestModel();
                  l5.setTestIntegerField(Long.valueOf(555));
                  l5.setTestField("w555w@meme.to");

                  testModel.setTestModels(Arrays.asList(l4, l5));

        mav.addObject("testModel", testModel);
        //model.addAttribute("testModel", testModel);

        List<TestModel> tml = new ArrayList<TestModel>();
           TestModel l1 = new TestModel();
                     l1.setTestIntegerField(Long.valueOf(321));
                     l1.setTestField("sun@meme.to");

           TestModel l2 = new TestModel();
                     l2.setTestIntegerField(Long.valueOf(123));
                     l2.setTestField("nus@meme.to");

           TestModel l3 = new TestModel();
                     l3.setTestIntegerField(Long.valueOf(213));
                     l3.setTestField("snu@meme.to");

                     tml.add(l1);
                     tml.add(l2);
                     tml.add(l3);

        mav.addObject("testModelsList", tml);

        mav.setViewName("test");


        return mav;
    }

    @RequestMapping(value = "/test/{someData}", method = RequestMethod.GET)
    public ModelAndView getTest(@PathVariable String someData) {
        return new ModelAndView("test", "data", someData);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String getPost(Model model,
                                @Valid TestModel testModel, BindingResult bindingResult,
                                @RequestParam(value="id", required=false) Long appUserId,
                                SessionStatus sessionStatus) {


        //mav.addObject("modelMap", modelMap);
        //mav.addObject("data", testModel);
        //mav.addObject("errors", bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("err", bindingResult);

            System.out.printf("--------%s%n", bindingResult);
            System.out.printf("--------%s%n :: %s%n", model.asMap().keySet(), model.asMap().entrySet());
            //System.out.printf("========%s%n :: %s%n", testModel);


            return "test";
        }

        sessionStatus.setComplete();

        return "test";
    }

}