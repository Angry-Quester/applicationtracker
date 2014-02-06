package edu.khai.applicationtracker.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
@ContextConfiguration({"classpath:application-servlet.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class AppUserUserRoleDAOTest {
	final static Logger logger = Logger.getLogger(AppUserUserRoleDAOTest.class);

	@Autowired
	private ApplicationContext applicationContext;

	private AppUser appUser = null;
	private UserRole userRole = null;
	private AppUserUserRole appUserUserRole = null;

	private AppUserDAO appUserDAO = null;
	private UserRoleDAO userRoleDAO = null;
	private AppUserUserRoleDAO appUserUserRoleDAO = null;

	private static final String USER_ROLE = "ROLE_USER";

	@Before
	public void setUp() throws Exception {
		appUser = new AppUser();
		appUser.setUsername("myname@mailbox.net");
		appUser.setPassword("password");
		appUser.setAuthorities("ROLE_ADMIN");

		userRole = new UserRole();
		userRole.setAuthority(USER_ROLE);

		appUserUserRole = new AppUserUserRole();

		appUserDAO = (AppUserDAO)applicationContext.getBean("appUserDAO");
		userRoleDAO = (UserRoleDAO)applicationContext.getBean("userRoleDAO");
		appUserUserRoleDAO = (AppUserUserRoleDAO)applicationContext.getBean("appUserUserRoleDAO");
	}

	@After
	public void tearDown() throws Exception {
		appUser = null;
		userRole = null;
		appUserUserRole = null;
		appUserDAO = null;
		userRoleDAO = null;
		appUserUserRoleDAO = null;
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(true)
	public void testSaveAppUserUserRole() throws Exception {
		appUserDAO.saveAppUser(appUser);
		userRoleDAO.saveUserRole(userRole);

		appUserUserRole.setAppUser(appUser);
		appUserUserRole.setUserRole(userRole);

		appUserUserRoleDAO.saveAppUserUserRole(appUserUserRole);

		assertNotNull("primary key assigned", appUserUserRole.getAppUserUserRoleId());
			logger.info(appUserUserRole);
		assertNotNull(appUserUserRole.getAppUser());
		assertNotNull(appUserUserRole.getUserRole());
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(true)
	public void testAddAndRemoveAppUserUserRole() throws Exception {
		appUserDAO.saveAppUser(appUser);
		userRoleDAO.saveUserRole(userRole);

		appUserUserRole.setAppUser(appUser);
		appUserUserRole.setUserRole(userRole);

		appUserUserRoleDAO.saveAppUserUserRole(appUserUserRole);

		assertNotNull("Checking appUserUserRole's ID value="+appUserUserRole.getAppUserUserRoleId(),appUserUserRole.getAppUserUserRoleId());
			logger.info(appUserUserRole);
		assertEquals(appUserUserRole.getAppUser(), appUser);

		if (logger.isDebugEnabled()) {
			logger.debug("removing appUserUserRole...");
		}

		appUserUserRoleDAO.removeAppUserUserRole(appUserUserRole.getAppUserUserRoleId());

		assertNull(appUserUserRoleDAO.getAppUserUserRole(appUserUserRole.getAppUserUserRoleId()));
	}
}