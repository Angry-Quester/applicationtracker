package edu.khai.applicationtracker.dao;

import java.util.List;

import edu.khai.applicationtracker.model.AppUserUserRole;

public interface AppUserUserRoleDAO extends DAO {
	public List<AppUserUserRole> getAppUserUserRoles();
	public AppUserUserRole getAppUserUserRole(Long appUserUserRoleId);
	public void saveAppUserUserRole(AppUserUserRole appUserUserRole);
	public void removeAppUserUserRole(Long appUserUserRoleId);
}