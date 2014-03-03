package edu.khai.applicationtracker.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.khai.applicationtracker.dao.ApplicationDAO;
import edu.khai.applicationtracker.model.Application;

public class ApplicationDAOHibernate extends HibernateDaoSupport implements ApplicationDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Application> getApplications() {
		return getHibernateTemplate().find("from Application");
	}

	@Override
	public Application getApplication(Long id) {
		return getHibernateTemplate().get(Application.class, id);
	}

	@Override
	public void saveApplication(Application application) {
		getHibernateTemplate().saveOrUpdate(application);
	}

	@Override
	public void removeApplication(Long id) {
		Object application = getHibernateTemplate().load(Application.class, id);
		getHibernateTemplate().delete(application);
	}
}
