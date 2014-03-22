package edu.khai.applicationtracker.service;

import edu.khai.applicationtracker.model.AppUser;

public interface AppUserService {
	public AppUser getAppUser(Long appUserId);
	public AppUser addAppUser(AppUser appUser);
	public AppUser updateAppUser(AppUser appUser);
	public void removeAppUser(Long appUserId);

	public AppUser getAppUserByName(String appUserName);
	public AppUser getAppUserByNameWithRoles(String appUserName);
}
