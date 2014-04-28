package edu.khai.applicationtracker.dao;

import edu.khai.applicationtracker.model.AppUser;

public interface AppUserDAO extends GenericDao<AppUser, Long> {
    public AppUser getAppUserByName(String name);
    public AppUser getAppUserByNameWithRoles(String name);
}