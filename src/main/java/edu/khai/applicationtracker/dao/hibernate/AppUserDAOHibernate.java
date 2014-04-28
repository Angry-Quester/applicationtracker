package edu.khai.applicationtracker.dao.hibernate;


import java.util.List;

import edu.khai.applicationtracker.dao.AppUserDAO;
import edu.khai.applicationtracker.model.AppUser;


public class AppUserDAOHibernate extends HibernateDAO<AppUser, Long> implements AppUserDAO {

    @Override
    @SuppressWarnings("unchecked")
    public AppUser getAppUserByName(String username) {
        List<AppUser> appUsers = currentSession()
                .createQuery("from AppUser where username=:username")
                .setParameter("username", username)
                .list();
        return (appUsers != null && appUsers.size() >0) ? appUsers.get(0) : null;
    }


    @Override
    @SuppressWarnings("unchecked")
    public AppUser getAppUserByNameWithRoles(String username) {
        List<AppUser> appUsers = currentSession()
                .createQuery("from AppUser au "
                        + "inner join fetch "
                        + "        au.userRoles ur "
                        + "where au.username=:username")
                        .setParameter("username", username)
                        .list();


        /*This stub makes my life simple, but it should be removed
         * as soon as the DB starts to fill with real data.
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