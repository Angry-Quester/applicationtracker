package edu.khai.applicationtracker.dao.hibernate;

import java.util.List;

import edu.khai.applicationtracker.dao.ApplicationDAO;
import edu.khai.applicationtracker.model.Application;

public class ApplicationDAOHibernate extends HibernateDAO<Application, Long> implements ApplicationDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Application> getApplicationsByAppUserId(Long appUserId) {

		return currentSession()
				.createQuery("select app from Application as app "
						+ "inner join app.appUserApplications auapp "
						+ "where auapp.appUser.appUserId=:appUserId")
				.setParameter("appUserId", appUserId)
				.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean securityCheck(Long principalId, Long entityId) {
		List<Application> applications =
				currentSession()
				.createQuery("select app from Application as app "
						+ "inner join app.appUserApplications auapp "
						+ "where auapp.appUser.appUserId=:principalId "
						+ "and app.applicationId=:entityId")
				.setParameter("principalId", principalId)
				.setParameter("entityId", entityId)
				.list();

			return (applications.size()>0) ? true : false;
	}

}
