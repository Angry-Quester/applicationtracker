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
@ContextConfiguration({"classpath:spring-context/dao-context.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class ApplicationDAOTestTut {
	final static Logger logger = Logger.getLogger(ApplicationDAOTestTut.class);

	@Autowired
	private ApplicationDAO applicationDAOTut;

	@Autowired
	private AppUserDAO appUserDAOTut;

	@Autowired
	private AppUserApplicationDAO appUserApplicationDAOTut;

	Application application = null;

	@Before
	public void setUp() throws Exception {

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
		application = null;
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(true)
	public void testAdd() {
		applicationDAOTut.add(application);
			logger.info("\n Application DATA ::"+application+"\n");
		assertNotNull(application.getApplicationId());
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(true)
	public void testUpdate() {
		String testFamilyName = "Trump";

		applicationDAOTut.add(application);
			logger.info("\n Application DATA BEFORE::"+application+"\n");

		application.setFamilyName(testFamilyName);
		applicationDAOTut.update(application);

		Application applicationUpdated =
				applicationDAOTut.find(application.getApplicationId());
			logger.info("\n Application DATA AFTER ::"+applicationUpdated+"\n");

		assertEquals(applicationUpdated.getFamilyName(), testFamilyName);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(true)
	public void testFind() {
		applicationDAOTut.add(application);

		Application found = applicationDAOTut.find(application.getApplicationId());

		assertEquals(application.getApplicationId(), found.getApplicationId());
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(true)
	public void testList() {
		applicationDAOTut.add(application);

		List<Application> applications = applicationDAOTut.list();

		assertEquals(applications.size(), 1);

		for (Application appl : applications) {
			assertTrue(applications.contains(appl));
		}

	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(true)
	public void testRemove() {
		applicationDAOTut.add(application);
			assertEquals(application, applicationDAOTut.find(application.getApplicationId()));
		applicationDAOTut.remove(application);
		Application foundAfterRemoval = applicationDAOTut.find(application.getApplicationId());
			assertNull(foundAfterRemoval);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(true)
	public void testRemoveById() {
		applicationDAOTut.add(application);
			assertEquals(application, applicationDAOTut.find(application.getApplicationId()));

		applicationDAOTut.removeById(application.getApplicationId());
		Application foundAfterRemoval = applicationDAOTut.find(application.getApplicationId());
			assertNull(foundAfterRemoval);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(true)
	public void testGetApplicationsByAppUserId () {
		logger.info("\n application DATA BEFORE::" + application + "\n");
		applicationDAOTut.add(application);
			logger.info("\n application DATA ::" + application + "\n");

		AppUser appUser = new AppUser();
		appUser.setUsername("myname@mailbox.net");
		appUser.setPassword("password");
		appUser.setAccountNonExpired(true);
		appUser.setAccountNonLocked(true);
		appUser.setCredentialsNonExpired(true);
		appUser.setEnabled(true);

		appUserDAOTut.add(appUser);
			logger.info("\n appUser DATA ::" + appUser + "\n");

		AppUserApplication aua = new AppUserApplication();
		aua.setApplication(application);
		aua.setAppUser(appUser);

		appUserApplicationDAOTut.add(aua);
			logger.info("\n appUserApplication DATA ::" + aua + "\n");

		List<Application> la = applicationDAOTut.getApplicationsByAppUserId(appUser.getAppUserId());
			logger.info("\n la DATA ::" + la + "\n");

		assertEquals(la.size(), 2);
	}
}
