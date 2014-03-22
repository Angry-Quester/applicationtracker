package edu.khai.applicationtracker.dao;

import org.apache.log4j.Logger;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.model.AppUserUserRole;
import edu.khai.applicationtracker.model.UserRole;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context/dao-context.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class AppUserDAOTestTut {
	final static Logger logger = Logger.getLogger(AppUserDAOTestTut.class);

	@Autowired
	AppUserDAO appUserDAOTut;

	@Autowired
	UserRoleDAO userRoleDAOTut;

	@Autowired
	AppUserUserRoleDAO appUserUserRoleDAOTut;

	private AppUser appUser = null;

	@Before
	public void setUp() throws Exception {
		appUser = new AppUser();
		appUser.setUsername("myname@mailbox.net");
		appUser.setPassword("password");
		appUser.setAccountNonExpired(true);
		appUser.setAccountNonLocked(true);
		appUser.setCredentialsNonExpired(true);
		appUser.setEnabled(true);
	}

	@After
	public void tearDown() throws Exception {
		appUser = null;
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(true)
	public void testGetAppUserByName() throws Exception {
		appUserDAOTut.add(appUser);

		AppUser found = appUserDAOTut.getAppUserByName(appUser.getUsername());

		logger.info("\n appUser DATA ::" + appUser + "\n");
		logger.info("\n found DATA ::" + found + "\n");

		assertEquals(found.getAppUserId(), appUser.getAppUserId());
	}

	/** The test is not corrent, and it has to be rewritten in the near future.
	 *
	 * It does correct type of request but i can't get "fetch" data from it
	 * due to the type of transaction this code is working in.
	 * Detached collection simply doesn't det into the DB
	 *
	 * So there is simply nothing to fetch.
	 */

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(true)
	public void testGetAppUserByNameWithRoles() throws Exception {
			appUserDAOTut.add(appUser);
				logger.info("\n appUser DATA ::" + appUser + "\n");

		UserRole userRole = new UserRole();
		userRole.setAuthority("ROLE_ADMIN");
			userRoleDAOTut.add(userRole);
				logger.info("\n userRole DATA ::" + userRole + "\n");

		AppUserUserRole auur = new AppUserUserRole();
		auur.setAppUser(appUser);
		auur.setUserRole(userRole);
			appUserUserRoleDAOTut.add(auur);
				logger.info("\n appUserUserRole DATA ::" + auur + "\n");

		AppUser found = appUserDAOTut.getAppUserByNameWithRoles(appUser.getUsername());
				logger.info("\n found appUser DATA ::" + found + "\n");

		assertEquals(found.getAppUserId(), appUser.getAppUserId());
	}
}
