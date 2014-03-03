package edu.khai.applicationtracker.dao;

import java.util.List;

import edu.khai.applicationtracker.model.AppUserApplication;

public interface AppUserApplicationDAO extends DAO {
	public List<AppUserApplication> getAppUserApplications();
	public AppUserApplication getAppUserApplication(Long appUserApplicationId);
	public void saveAppUserApplication(AppUserApplication appUserApplication);
	public void removeAppUserApplication(Long appUserApplicationId);
}