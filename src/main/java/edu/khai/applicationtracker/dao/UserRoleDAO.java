package edu.khai.applicationtracker.dao;

import java.util.List;

import edu.khai.applicationtracker.model.UserRole;

public interface UserRoleDAO extends DAO {
	public List<UserRole> getUserRoles();
	public UserRole getUserRole(Long userRoleId);
	public void saveUserRole(UserRole userRole);
	public void removeUserRole(Long userRoleId);
}