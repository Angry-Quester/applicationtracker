package edu.khai.applicationtracker.service.impl;

import java.util.List;

import edu.khai.applicationtracker.dao.AppUserDAO;
import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.service.AppUserManager;

public class AppUserManagerImpl implements AppUserManager{

	private AppUserDAO appUserDAO;

	public void setAppUserDAO(AppUserDAO appUserDAO) {
		this.appUserDAO = appUserDAO;
	}

	@Override
	public List<AppUser> getAppUsers() {
		return appUserDAO.getAppUsers();
	}

	@Override
	public AppUser getAppUser(Long appUserId) {
		return appUserDAO.getAppUser(appUserId);
	}

	@Override
	public AppUser getAppUserByName(String appUserName) {
		return appUserDAO.getAppUserByName(appUserName);
	}

	@Override
	public AppUser saveAppUser(AppUser appUser) {
		appUserDAO.saveAppUser(appUser);
		return appUser;
	}

	@Override
	public void removeAppUser(Long appUserId) {
		appUserDAO.removeAppUser(appUserId);

	}

}
