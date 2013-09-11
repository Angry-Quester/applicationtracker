package edu.khai.appticationtracker.dao;

import java.util.List;

import edu.khai.appticationtracker.model.Application;

public interface ApplicationDAO extends DAO {
	public List<Application> getApplications();
	public Application getApplication(Long applicationId);
	public void saveApplication(Application application);
    public void removeApplication(Long applicationId);
}