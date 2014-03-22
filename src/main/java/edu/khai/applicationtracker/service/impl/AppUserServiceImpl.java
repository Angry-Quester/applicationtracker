package edu.khai.applicationtracker.service.impl;

import edu.khai.applicationtracker.dao.AppUserDAO;
import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.service.AppUserService;

public class AppUserServiceImpl implements AppUserService{

	private AppUserDAO appUserDAO;

	public void setAppUserDAO(AppUserDAO appUserDAO) {
		this.appUserDAO = appUserDAO;
	}

	@Override
	public AppUser getAppUser(Long appUserId) {
		return appUserDAO.find(appUserId);
	}

	@Override
	public AppUser addAppUser(AppUser appUser) {
		appUserDAO.add(appUser);
		return appUser;
	}

	@Override
	public AppUser updateAppUser(AppUser appUser) {
		appUserDAO.update(appUser);
		return appUser;
	}

	@Override
	public void removeAppUser(Long appUserId) {
		appUserDAO.removeById(appUserId);
	}

	@Override
	public AppUser getAppUserByName(String appUserName) {
		return appUserDAO.getAppUserByName(appUserName);
	}

	@Override
	public AppUser getAppUserByNameWithRoles(String appUserName) {
		return appUserDAO.getAppUserByName(appUserName);
	}
}
