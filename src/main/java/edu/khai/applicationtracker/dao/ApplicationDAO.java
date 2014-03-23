package edu.khai.applicationtracker.dao;

import java.util.List;

import edu.khai.applicationtracker.model.Application;

public interface ApplicationDAO extends GenericDao<Application, Long>, SecuredDAO{
	public List<Application> getApplicationsByAppUserId(Long appUserId);
}