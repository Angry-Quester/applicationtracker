package edu.khai.applicationtracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.model.AppUserPrincipal;
import edu.khai.applicationtracker.service.AppUserService;
import edu.khai.applicationtracker.service.ApplicationService;


@Controller
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ApplicationService applicationManager;


    @RequestMapping(value = "/appUsers", method = RequestMethod.GET)
    public String getAppUsers(Model model) {
        //getting authenticated user name
        AppUserPrincipal aup =
                (AppUserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //use the name to load appUser
        AppUser au = appUserService.getAppUser(aup.getUserId());

        List<AppUser> appUsers = new ArrayList<AppUser>();
                        appUsers.add(appUserService.getAppUser(au.getAppUserId()));

            model.addAttribute("appUsers", appUsers);

        return "appUsers";
    }

    @RequestMapping(value = "/appUsers/{appUserId}", method = RequestMethod.GET)
    public String getAppUserById(@PathVariable Long appUserId, Model model) {

        AppUser appUser = appUserService.getAppUser(appUserId);
            model.addAttribute("appUser", appUser);

        return "appUser";
    }

}