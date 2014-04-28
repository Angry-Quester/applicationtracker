package edu.khai.applicationtracker.service.impl;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import edu.khai.applicationtracker.dao.AppUserDAO;
import edu.khai.applicationtracker.dao.ApplicationDAO;
import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.model.AppUserPrincipal;
import edu.khai.applicationtracker.model.Application;
import edu.khai.applicationtracker.service.ApplicationService;

public class ApplicationServiceImpl implements ApplicationService{

    private AppUserDAO appUserDAO;
    private ApplicationDAO applicationDAO;

    /**
     * @param appUserDAO the appUserDAO to set
     */
    public void setAppUserDAO(AppUserDAO appUserDAO) {
        this.appUserDAO = appUserDAO;
    }

    /**
     * @param applicationDAO the applicationDAO to set
     */
    public void setApplicationDAO(ApplicationDAO applicationDAO) {
        this.applicationDAO = applicationDAO;
    }

    @Override
    public List<Application> getApplicationsByAppUserId(Long appUserId) {
        return applicationDAO.getApplicationsByAppUserId(appUserId);
    }

    @Override
//    @PreAuthorize("hasRole('ROLE_ADMIN')" )
//    @PostAuthorize("hasPermission(returnObject, admin)")
    public Application getApplication(Long applicationId) {
        Application application = applicationDAO.find(applicationId);
        return application;
    }

    @Override
    public Application addApplication(Application application) {

        //persist an application
        applicationDAO.add(application);

        //get user entity and update it's applications list
        AppUser appUser = this.getAppUserFromPrincipal();

        //add link between Appuser and Application
        application.addToUsers(appUser);
        //save the link
        //        appUserDAO.update(appUser);
        //link saves automatically after the session flush()

        return application;
    }

    @Override
    public Application updateApplication(Application application) {
        //update doesn't affect link on the inverse side of the association
        applicationDAO.update(application);

        return application;
    }

    @Override
    public void removeApplication(Long applicationId) {
        Application application = applicationDAO.find(applicationId);

        //get current secured user entity
        AppUser appUser = this.getAppUserFromPrincipal();

        //severe the link between AppUser and Application beforehand
        application.removeFromUsers(appUser);

        //and remove application
        applicationDAO.remove(application);
    }

    private AppUser getAppUserFromPrincipal() {
        //get authenticated user ID to get AppUser afterwards
        AppUserPrincipal aup =
                (AppUserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return appUserDAO.find(aup.getUserId());
    }

}
