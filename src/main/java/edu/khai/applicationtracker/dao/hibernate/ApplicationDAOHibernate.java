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
                        + "inner join app.appUsers au "
                        + "where au.appUserId=:appUserId")
                .setParameter("appUserId", appUserId)
                .list();
    }

}
