package edu.khai.applicationtracker.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.SimpleDateFormat;

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
import edu.khai.applicationtracker.model.AppUserApplication;
import edu.khai.applicationtracker.model.Application;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:application-servlet.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class AppUserApplicationDAOTest {
	final static Logger logger = Logger.getLogger(AppUserApplicationDAOTest.class);

	@Autowired
	private ApplicationContext applicationContext;

	private AppUser appUser = null;
	private Application application = null;
	private AppUserApplication appUserApplication = null;

	private AppUserDAO appUserDAO = null;
	private ApplicationDAO applicationDAO = null;
	private AppUserApplicationDAO appUserApplicationDAO = null;

	@Before
	public void setUp() throws Exception {
		appUser = new AppUser();
		appUser.setUsername("myname@mailbox.net");
		appUser.setPassword("password");
		appUser.setAuthorities("ROLE_ADMIN");

		application = new Application();
		application.setGivenName("John");
		application.setFamilyName("Layman");
		application.setMiddleName("Doe");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				application.setBirthDate(sdf.parse("12-10-1960"));
				application.setCreationDate(sdf.parse("02-01-2014"));
				application.setLastModificationDate(sdf.parse("13-01-2014"));

		appUserApplication = new AppUserApplication();

		appUserDAO = (AppUserDAO)applicationContext.getBean("appUserDAO");
		applicationDAO = (ApplicationDAO)applicationContext.getBean("applicationDAO");
		appUserApplicationDAO = (AppUserApplicationDAO)applicationContext.getBean("appUserApplicationDAO");
	}

	@After
	public void tearDown() throws Exception {
		appUser = null;
		application = null;
		appUserApplication = null;
		appUserDAO = null;
		applicationDAO = null;
		appUserApplicationDAO = null;
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(false)
	public void testSaveAppUserUserRole() throws Exception {
		appUserDAO.saveAppUser(appUser);
		applicationDAO.saveApplication(application);

		appUserApplication.setAppUser(appUser);
		appUserApplication.setApplication(application);

		appUserApplicationDAO.saveAppUserApplication(appUserApplication);

		assertNotNull("primary key assigned", appUserApplication.getAppUserApplicationId());
			logger.info(appUserApplication);
		assertNotNull(appUserApplication.getAppUser());
		assertNotNull(appUserApplication.getApplication());
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(false)
	public void testAddAndRemoveAppUserUserRole() throws Exception {
		appUserDAO.saveAppUser(appUser);
		applicationDAO.saveApplication(application);

		appUserApplication.setAppUser(appUser);
		appUserApplication.setApplication(application);

		appUserApplicationDAO.saveAppUserApplication(appUserApplication);

		assertNotNull("Checking appUserUserRole's ID value="+appUserApplication.getAppUserApplicationId(),appUserApplication.getAppUserApplicationId());
			logger.info(appUserApplication);
		assertEquals(appUserApplication.getAppUser(), appUser);

		if (logger.isDebugEnabled()) {
			logger.debug("removing appUserUserRole...");
		}

		appUserApplicationDAO.removeAppUserApplication(appUserApplication.getAppUserApplicationId());

		assertNull(appUserApplicationDAO.getAppUserApplication(appUserApplication.getAppUserApplicationId()));
	}
}