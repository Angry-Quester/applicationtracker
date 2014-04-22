package edu.khai.applicationtracker.service.impl;

import java.util.List;

import javax.persistence.CascadeType;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;

import edu.khai.applicationtracker.dao.AppUserApplicationDAO;
import edu.khai.applicationtracker.dao.AppUserDAO;
import edu.khai.applicationtracker.dao.ApplicationDAO;
import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.model.AppUserApplication;
import edu.khai.applicationtracker.model.AppUserPrincipal;
import edu.khai.applicationtracker.model.Application;
import edu.khai.applicationtracker.service.ApplicationService;

public class ApplicationServiceImpl implements ApplicationService{

	private AppUserDAO appUserDAO;
	private ApplicationDAO applicationDAO;
	private AppUserApplicationDAO appUserApplicationDAO;

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

	/**
	 * @param appUserApplicationDAO the appUserApplicationDAO to set
	 */
	public void setAppUserApplicationDAO(AppUserApplicationDAO appUserApplicationDAO) {
		this.appUserApplicationDAO = appUserApplicationDAO;
	}

	@Override
	public List<Application> getApplicationsByAppUserId(Long appUserId) {
		return applicationDAO.getApplicationsByAppUserId(appUserId);
	}

	@Override
//	@PreAuthorize("hasRole('ROLE_ADMIN')" )
//	@PostAuthorize("hasPermission(returnObject, admin)")
	public Application getApplication(Long applicationId) {
		Application application = applicationDAO.find(applicationId);
		return application;
	}

	@Override
	public Application addApplication(Application application) {

		applicationDAO.add(application);

		//get authenticated user ID to get AppUser upstream
		AppUserPrincipal aup =
				(AppUserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		//get user entity and update it's applications list
		AppUser appUser = appUserDAO.find(aup.getUserId());
		appUser.getApplications().add(application);

		appUserDAO.update(appUser);

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
