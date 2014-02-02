package edu.khai.applicationtracker.dao;

import java.util.List;

import edu.khai.applicationtracker.model.AppUser;

public interface AppUserDAO extends DAO {
	public List<AppUser> getAppUsers();
	public AppUser getAppUser(Long appUserId);
	public void saveAppUser(AppUser appUser);
    public void removeAppUser(Long appUserId);
    public AppUser getAppUserByName(String name);
}