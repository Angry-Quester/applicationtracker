

package edu.khai.applicationtracker.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.khai.applicationtracker.dao.AppUserDAO;
import edu.khai.applicationtracker.model.AppUser;

public class AppUserDAOHibernate  extends HibernateDaoSupport implements AppUserDAO {

	@SuppressWarnings("unchecked")
	public List<AppUser> getAppUsers(){
		return getHibernateTemplate().find("from AppUser");
	}
	
	public AppUser getAppUser(Long id) {
		return getHibernateTemplate().get(AppUser.class, id);
	}
	
	public void saveAppUser(AppUser appUser){
		getHibernateTemplate().saveOrUpdate(appUser);
	}
	
	public void removeAppUser(Long id) {
		Object appUser = getHibernateTemplate().load(AppUser.class, id);
		getHibernateTemplate().delete(appUser);
	}
	
	@SuppressWarnings("unchecked")
	public AppUser getAppUserByName(String name) {
		List<AppUser> appUsers = getHibernateTemplate().find("from AppUser where name=?", name);
		return (appUsers != null && appUsers.size() >0) ? appUsers.get(0) : null;
	}
	
}