package edu.khai.applicationtracker.service;

import java.util.List;

import edu.khai.applicationtracker.model.AppUser;

public interface AppUserManager {
    public List<AppUser> getAppUsers();
    public AppUser getAppUser(Long applicationId);
    public AppUser saveAppUser(AppUser appUser);
    public void removeAppUser(Long appUserId);
}
