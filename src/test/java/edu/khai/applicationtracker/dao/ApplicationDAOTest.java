package edu.khai.applicationtracker.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.khai.applicationtracker.model.Application;
import static edu.khai.applicationtracker.dao.DBUnitSetup.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context/dao-context.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class ApplicationDAOTest{
	final static Logger logger = Logger.getLogger(ApplicationDAOTest.class);

	//path to the prepeared DBUnit flat dataset
	private static final String DATASET_PATH = "src/test/resources/dbunit/ApplicationDataSet.xml";

	private IDatabaseTester databaseTester = null;
	private IDataSet dataSet = null;

	@Autowired
	private DataSource dataSource; 	//ComboPooledDataSource

	@Autowired
	private ApplicationDAO applicationDAO;


	@Before
	public void setUp() throws Exception {
		dataSet = buildDataSet(DATASET_PATH);
		databaseTester = buildUpDatabaseTester(dataSource, dataSet);
		databaseTester.onSetup();
	}


	@After
	public void tearDown() throws Exception {
		//databaseTester.onTearDown();
	}

	@AfterClass
	public static void afterClass() throws Exception {
		DBUnitSetup.afterTestCleanUp(DATASET_PATH);
	}


	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(true)
	public void testAdd() throws Exception{
		//Creating some random application for the test
		Application application = null;
		application = new Application();
		application.setGivenName("John");
		application.setFamilyName("Layman");
		application.setMiddleName("Doe");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				application.setBirthDate(sdf.parse("12-10-1960"));
				application.setCreationDate(sdf.parse("02-01-2014"));
				application.setLastModificationDate(sdf.parse("13-01-2014"));

		//saving new application
		applicationDAO.add(application);
			logger.info("\n Application DATA ::"+application+"\n");

		//asserting that the new application now has a brand new id
		assertNotNull("Application id is null!!! :: ",
						application.getApplicationId());
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(true)
	public void testUpdate() throws Exception{
		//get data from the dataset to check against
		Long applicationId = Long.valueOf(getTestData("application", 0, "applicationId"));
		String familyName = getTestData("application", 0, "familyName");

		//get the application to update
		Application application = applicationDAO.find(applicationId);
			logger.info("\n Application DATA BEFORE::"+application+"\n");

		//set new name to check updated data
		application.setFamilyName("Travolta");
		applicationDAO.update(application);
			logger.info("\n Application DATA AFTER::"+application+"\n");

		//get updated application from the database
		Application applicationUpdated =
				applicationDAO.find(applicationId);
			logger.info("\n Application DATA AFTER CHECK::"+applicationUpdated+"\n");

		//Assert applications IDs
		assertEquals("Updated and initial application have different ID's",
						applicationId,
						applicationUpdated.getApplicationId());
		//Assert changed data
		assertNotSame("The data hasn't changed!",
						familyName,
						applicationUpdated.getFamilyName());
	}


	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(true)
	public void testFind() throws Exception {
		//get data from the dataset to check against
		Long applicationId = Long.valueOf(getTestData("application", 0, "applicationId"));
		String familyName = getTestData("application", 0, "familyName");

		//Find application by id
		Application found = applicationDAO.find(applicationId);

		//assert found application id
		assertEquals("Found application has the wrong id!!! ::",
						applicationId,
						found.getApplicationId());
		//assert found application data
		assertEquals("Found application has wrong data inside it",
						familyName,
						found.getFamilyName());
	}


	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(true)
	public void testList() throws Exception {
		//get row count from the application table
		int rowCount =  getRowCount("application");

		//get applications list
		List<Application> la = applicationDAO.list();

		//assert applications list size
		assertEquals("Applications list has wrong number of records!!! :: ",
						rowCount,
						la.size());
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(true)
	public void testRemove() throws Exception {
		//get row count from the application table
		int rowCount =  getRowCount("application");
		//take the application before last - so it doesn't have any dependencies
		Long applicationId = Long.valueOf(getTestData("application", rowCount-1, "applicationId"));

		//get an application entity
		Application application = applicationDAO.find(applicationId);
			logger.info("\n Application DATA BEFORE::"+application+"\n");


		//remove one application
		applicationDAO.remove(application);

		//get an application entity
		Application found = applicationDAO.find(applicationId);
			logger.info("\n Application DATA after::"+found+"\n");


		///assert that deleted application is null
		assertNull("Application hasn't been deleted for some reason!!! :: ",
						found);


		//get applications list
		List<Application> la = applicationDAO.list();


		//assert applications list size has decreased
		assertEquals("Application list has the same size as before the removal operatino!!! :: ",
						rowCount-1,
						la.size());
	}


	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(true)
	public void testRemoveById() throws Exception {
		//get row count from the application table
		int rowCount =  getRowCount("application");
		//take the application before last - so it doesn't have any dependencies
		Long applicationId = Long.valueOf(getTestData("application", rowCount-1, "applicationId"));

		//get an application entity
		Application application = applicationDAO.find(applicationId);
			logger.info("\n Application DATA BEFORE::"+application+"\n");


		//remove one application
		applicationDAO.removeById(applicationId);

		//get an application entity
		Application found = applicationDAO.find(applicationId);
			logger.info("\n Application DATA after::"+found+"\n");

		///assert that deleted application is null
		assertNull("Application hasn't been deleted for some reason!!! :: ",
						found);

		//get applications list
		List<Application> la = applicationDAO.list();

		//assert applications list size has decreased
		assertEquals("Application list has the same size as before the removal operatinon!!! :: ",
						rowCount-1,
						la.size());
	}


	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(true)
	public void testGetApplicationsByAppUserId () throws Exception {
		//get data from the dataset to check against
		Long appUserId = Long.valueOf(getTestData("appUser", 0, "appUserId"));

		List<Application> la = applicationDAO.getApplicationsByAppUserId(appUserId);
			logger.info("\n Found applications list size :: " + la.size() + "\n");

		assertNotNull("Couldn't get applications for the user by his ID = " + appUserId + "!!! :: ",
						la);
	}


}
