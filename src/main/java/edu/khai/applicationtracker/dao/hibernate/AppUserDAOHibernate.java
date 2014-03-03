package edu.khai.applicationtracker.dao.hibernate;


import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import edu.khai.applicationtracker.dao.AppUserDAO;
import edu.khai.applicationtracker.model.AppUser;


public class AppUserDAOHibernate extends HibernateDaoSupport implements AppUserDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<AppUser> getAppUsers(){
		return getHibernateTemplate().find("from AppUser");
	}

	@Override
	public AppUser getAppUser(Long id) {
		return getHibernateTemplate().get(AppUser.class, id);
	}

	@Override
	public void saveAppUser(AppUser appUser){
		getHibernateTemplate().saveOrUpdate(appUser);
	}

	@Override
	public void removeAppUser(Long id) {
		Object appUser = getHibernateTemplate().load(AppUser.class, id);
		getHibernateTemplate().delete(appUser);
	}

	@Override
	@SuppressWarnings("unchecked")
	public AppUser getAppUserFromName(String username) {
		List<AppUser> appUsers = getHibernateTemplate().find("from AppUser where username=?", username);
		return (appUsers != null && appUsers.size() >0) ? appUsers.get(0) : null;
	}


	@Override
	@SuppressWarnings("unchecked")
	public AppUser getAppUserFromNameWithRoles(String username) {
		List<AppUser> appUsers = getHibernateTemplate().find("from AppUser au "
				+ "inner join fetch "
				+ "		au.appUserUserRoles auur "
				+ "inner join fetch "
				+ "		auur.userRole ur "
				+ "where au.username=?", username);

		/*This stub makes my life simple, but it should be removed
		 * as soon as the DB starts to fill with rea data.
		 *For now users duplicate for known reasons - tests create duplicates
		 */
			AppUser appUser = (appUsers != null && appUsers.size() >0) ? appUsers.get(0) : null;

		/* I will leave this code here, as an alternative.
		 * The only reason i don't use it is this code requires
		 * transaction cjntext to be valid.
		 * HQL query does fetching using only one request.
		 */
			/*
				Hibernate.initialize(appUser.getAppUserUserRoles());

					Iterator<AppUserUserRole> i = appUser.getAppUserUserRoles().iterator();

					while (i.hasNext()) {
						Hibernate.initialize(i.next().getUserRole());
					}
			*/
		return appUser;
	}

}