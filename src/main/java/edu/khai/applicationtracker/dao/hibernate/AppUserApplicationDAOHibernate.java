package edu.khai.applicationtracker.dao.hibernate;

import edu.khai.applicationtracker.dao.AppUserApplicationDAO;
import edu.khai.applicationtracker.model.AppUserApplication;


public class AppUserApplicationDAOHibernate  extends HibernateDAO<AppUserApplication, Long>
												implements AppUserApplicationDAO {

}