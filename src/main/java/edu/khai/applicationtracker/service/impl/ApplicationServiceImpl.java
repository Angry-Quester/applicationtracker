package edu.khai.applicationtracker.service.impl;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import edu.khai.applicationtracker.dao.ApplicationDAO;
import edu.khai.applicationtracker.model.Application;
import edu.khai.applicationtracker.service.ApplicationService;

public class ApplicationServiceImpl implements ApplicationService{

	private ApplicationDAO applicationDAO;

	public void setApplicationDAO(ApplicationDAO applicationDAO) {
		this.applicationDAO = applicationDAO;
	}

	@Override
	public List<Application> getApplicationsByAppUserId(Long appUserId) {
		return applicationDAO.getApplicationsByAppUserId(appUserId);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')" )
	@PostAuthorize("hasPermission(returnObject, admin)")
	public Application getApplication(Long applicationId) {
		Application application = applicationDAO.find(applicationId);
		return application;
	}

	@Override
	public Application addApplication(Application application) {
		applicationDAO.add(application);
		return application;
	}

	@Override
	public Application updateApplication(Application application) {
		applicationDAO.update(application);
		return application;
	}

	@Override
	public void removeApplication(Long applicationId) {
		applicationDAO.removeById(applicationId);
	}

}
