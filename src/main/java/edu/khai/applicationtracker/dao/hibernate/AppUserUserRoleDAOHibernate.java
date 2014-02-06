package edu.khai.applicationtracker.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.khai.applicationtracker.dao.AppUserUserRoleDAO;
import edu.khai.applicationtracker.model.AppUserUserRole;

public class AppUserUserRoleDAOHibernate  extends HibernateDaoSupport implements AppUserUserRoleDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<AppUserUserRole> getAppUserUserRoles(){
		return getHibernateTemplate().find("from AppUserUserRole");
	}

	@Override
	public AppUserUserRole getAppUserUserRole(Long id) {
		return getHibernateTemplate().get(AppUserUserRole.class, id);
	}

	@Override
	public void saveAppUserUserRole(AppUserUserRole appUserUserRole){
		getHibernateTemplate().saveOrUpdate(appUserUserRole);
	}

	@Override
	public void removeAppUserUserRole(Long id) {
		Object appUserUserRole = getHibernateTemplate().load(AppUserUserRole.class, id);
		getHibernateTemplate().delete(appUserUserRole);
	}

}