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

	public List<AppUser> getAppUsers() {
		return appUserDAO.getAppUsers();
	}

	public AppUser getAppUser(Long appUserId) {
		return appUserDAO.getAppUser(appUserId);
	}
	
	public AppUser saveAppUser(AppUser appUser) {
		appUserDAO.saveAppUser(appUser);
		return appUser;
	}

	public void removeAppUser(Long appUserId) {
		appUserDAO.removeAppUser(appUserId);
		
	}

}
