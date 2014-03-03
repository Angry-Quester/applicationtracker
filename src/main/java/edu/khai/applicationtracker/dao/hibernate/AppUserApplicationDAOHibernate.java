package edu.khai.applicationtracker.dao.hibernate;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import edu.khai.applicationtracker.dao.AppUserApplicationDAO;
import edu.khai.applicationtracker.model.AppUserApplication;


public class AppUserApplicationDAOHibernate  extends HibernateDaoSupport implements AppUserApplicationDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<AppUserApplication> getAppUserApplications(){
		return getHibernateTemplate().find("from AppUserApplication");
	}

	@Override
	public AppUserApplication getAppUserApplication(Long id) {
		return getHibernateTemplate().get(AppUserApplication.class, id);
	}

	@Override
	public void saveAppUserApplication(AppUserApplication appUserApplication){
		getHibernateTemplate().saveOrUpdate(appUserApplication);
	}

	@Override
	public void removeAppUserApplication(Long id) {
		Object appUserApplication = getHibernateTemplate().load(AppUserApplication.class, id);
		getHibernateTemplate().delete(appUserApplication);
	}

}