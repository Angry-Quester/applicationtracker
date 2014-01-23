package edu.khai.applicationtracker.service;

import java.util.List;

import edu.khai.applicationtracker.model.Application;

public interface ApplicationManager {
    public List<Application> getApplications();
    public Application getApplication(String applicationId);
    public Application saveApplication(Application application);
    public void removeApplication(String applicationId);
}
