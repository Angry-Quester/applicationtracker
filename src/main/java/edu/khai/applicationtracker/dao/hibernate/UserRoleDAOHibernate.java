

package edu.khai.applicationtracker.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.khai.applicationtracker.dao.UserRoleDAO;
import edu.khai.applicationtracker.model.UserRole;

public class UserRoleDAOHibernate  extends HibernateDaoSupport implements UserRoleDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<UserRole> getUserRoles(){
		return getHibernateTemplate().find("from UserRole");
	}

	@Override
	public UserRole getUserRole(Long userRoleId) {
		return getHibernateTemplate().get(UserRole.class, userRoleId);
	}

	@Override
	public void saveUserRole(UserRole userRole){
		getHibernateTemplate().saveOrUpdate(userRole);
	}

	@Override
	public void removeUserRole(Long userRoleId) {
		Object userRole = getHibernateTemplate().load(UserRole.class, userRoleId);
		getHibernateTemplate().delete(userRole);
	}

}