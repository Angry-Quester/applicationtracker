package edu.khai.applicationtracker.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;

import static org.junit.Assert.*;

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
import edu.khai.applicationtracker.dao.ApplicationDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context/dao-context.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class ApplicationDAOTest {
	final static Logger logger = Logger.getLogger(ApplicationDAOTest.class);

	@Autowired
	private ApplicationContext applicationContext;

	private Application application = null;
	private ApplicationDAO applicationDAO = null;
	private AppUserDAO appUserDAO = null;
	private AppUserApplicationDAO appUserApplicationDAO = null;

	@Before
	public void setUp() throws Exception {
		//Getting ApplicationDAO bean from the spring context
		applicationDAO = (ApplicationDAO) applicationContext.getBean("applicationDAO");
		appUserDAO = (AppUserDAO) applicationContext.getBean("appUserDAO");
		appUserApplicationDAO = (AppUserApplicationDAO) applicationContext.getBean("appUserApplicationDAO");

		//Creating some random application for the test
		application = new Application();
		application.setGivenName("John");
		application.setFamilyName("Layman");
		application.setMiddleName("Doe");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				application.setBirthDate(sdf.parse("12-10-1960"));
				application.setCreationDate(sdf.parse("02-01-2014"));
				application.setLastModificationDate(sdf.parse("13-01-2014"));
	}

	@After
	public void tearDown() throws Exception {
		applicationDAO = null;
		application = null;
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(false)
	public void testSaveApplication() throws Exception {

		applicationDAO.saveApplication(application);

		assertNotNull("primary key assigned", application.getApplicationId());
			logger.info(application);
		assertNotNull(application.getBirthDate());
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(false)
	public void testAddAndRemoveApplication() throws Exception {
		applicationDAO.saveApplication(application);

		assertNotNull("Checking application's ID value="+application.getApplicationId(),application.getApplicationId());
			logger.info(application);
		assertEquals(application.getGivenName(), "John");

		if (logger.isDebugEnabled()) {
			logger.debug("removing application...");
		}

		applicationDAO.removeApplication(application.getApplicationId());

		assertNull(applicationDAO.getApplication(application.getApplicationId()));
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(false)
	public void testGetApplicationsByAppUserId() throws Exception {
		applicationDAO.saveApplication(application);

			AppUser appUser = new AppUser();
			appUser.setUsername("myname@mailbox.net");
			appUser.setPassword("password");

			appUserDAO.saveAppUser(appUser);

				AppUserApplication appUserApplication = new AppUserApplication();
				appUserApplication.setAppUser(appUser);
				appUserApplication.setApplication(application);

				appUserApplicationDAO.saveAppUserApplication(appUserApplication);

		List<Application> lp = applicationDAO.getApplicationsByAppUserId(appUser.getAppUserId());

		logger.info("Application for the user List content = "+lp.get(0));

			assertTrue(lp.size()>0);
			assertEquals(lp.get(0).getFamilyName(), "Layman");
	}


}
