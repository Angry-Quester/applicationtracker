package edu.khai.applicationtracker.dao;

import java.util.Iterator;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
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


import edu.khai.applicationtracker.dao.AppUserDAO;
import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.model.AppUserUserRole;
import edu.khai.applicationtracker.model.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context/dao-context.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class AppUserDAOTest {

	final static Logger logger = Logger.getLogger(AppUserDAOTest.class);

	//path to the prepeared DBUnit flat dataset
	private static final String DATASET_PATH = "src/test/resources/dbunit/AppUserDataSet.xml";

	private IDatabaseTester databaseTester = null;
	private IDataSet dataSet = null;

	@Autowired
	DataSource dataSource; 	//ComboPooledDataSource

	@Autowired
	AppUserDAO appUserDAO;

	@Before
	public void setUp() throws Exception {
		dataSet = DBUnitSetup.readDataSet(DATASET_PATH);
		databaseTester = DBUnitSetup.setUpDatabaseTester(dataSource, dataSet);
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
	@Rollback(false)
	public void testGetAppUserByName() throws Exception {
		//get data from the dataset to check against
		Long appUserId = Long.valueOf(DBUnitSetup.getTestData("appUser", 0, "appUserId"));
		String username = DBUnitSetup.getTestData("appUser", 0, "username");

		//get appuser from database
		AppUser found = appUserDAO.getAppUserByName(username);
			logger.info("\n found appUser DATA ::" + found + "\n");

		//assert id and name
		assertEquals(found.getAppUserId(), appUserId);
		assertEquals(found.getUsername(), username);
	}


	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(false)
	public void testGetAppUserByNameWithRoles() throws Exception {
		//get data from the dataset to check against
		Long appUserId = Long.valueOf(DBUnitSetup.getTestData("appUser", 0, "appUserId"));
		String username = DBUnitSetup.getTestData("appUser", 0, "username");

		//get appuser from database
		AppUser found = appUserDAO.getAppUserByNameWithRoles(username);
			logger.info("\n found appUser DATA ::" + found + "\n");

		//assert id and name
		assertEquals(found.getAppUserId(), appUserId);
		assertEquals(found.getUsername(), username);

			logger.info("\n found AppUserUserRoles roles ::" + found.getAppUserUserRoles() + "\n");
		//assert AppUserUserRoles is present in the resulting dataset
		assertTrue(found.getAppUserUserRoles().size()>0);

			//assert roles are not null
			Iterator<AppUserUserRole> iauur = found.getAppUserUserRoles().iterator();
			while(iauur.hasNext()) {
				UserRole ur = iauur.next().getUserRole();
					logger.info("\n found UserRoles roles ::" + ur + "\n");
				assertNotNull(ur);
			}
	}

}
