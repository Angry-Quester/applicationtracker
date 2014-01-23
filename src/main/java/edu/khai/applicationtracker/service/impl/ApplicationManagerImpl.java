package edu.khai.applicationtracker.service.impl;

import java.util.List;

import edu.khai.applicationtracker.dao.ApplicationDAO;
import edu.khai.applicationtracker.model.Application;

import edu.khai.applicationtracker.service.ApplicationManager;

public class ApplicationManagerImpl implements ApplicationManager{

    private ApplicationDAO applicationDAO;

    public void setApplicationDAO(ApplicationDAO applicationDAO) {
        this.applicationDAO = applicationDAO;
    }

    public List<Application> getApplications() {
        return applicationDAO.getApplications();
    }

    public Application getApplication(String applicationId) {
        Application application = applicationDAO.getApplication(Long.valueOf(applicationId));
        return application;
    }

    public Application saveApplication(Application application) {
        applicationDAO.saveApplication(application);
        return application;
    }

    public void removeApplication(String applicationId) {
        applicationDAO.removeApplication(Long.valueOf(applicationId));
    }

}
