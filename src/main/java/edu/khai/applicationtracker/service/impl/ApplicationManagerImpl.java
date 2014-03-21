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

	@Override
	public List<Application> getApplications() {
		return applicationDAO.getApplications();
	}

	@Override
	public List<Application> getApplicationsByAppUserId(Long appUserId) {
		return applicationDAO.getApplicationsByAppUserId(appUserId);
	}

	@Override
	public Application getApplication(Long applicationId) {
		Application application = applicationDAO.getApplication(applicationId);
		return application;
	}

	@Override
	public Application saveApplication(Application application) {
		applicationDAO.saveApplication(application);
		return application;
	}

	@Override
	public void removeApplication(Long applicationId) {
		applicationDAO.removeApplication(applicationId);
	}

}
