package edu.khai.applicationtracker.service;

import java.util.List;

import edu.khai.applicationtracker.model.Application;

public interface ApplicationService extends SecuredService{
	public Application getApplication(Long applicationId);
	public List<Application> getApplicationsByAppUserId(Long appUserId);
	public Application addApplication(Application application);
	public Application updateApplication(Application application);
	public void removeApplication(Long applicationId);
}