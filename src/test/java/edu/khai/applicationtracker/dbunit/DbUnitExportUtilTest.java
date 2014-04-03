package edu.khai.applicationtracker.dbunit;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.DefaultDatabaseTester;
import org.dbunit.DefaultOperationListener;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.statement.IStatementFactory;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableIterator;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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

import com.mchange.v2.c3p0.ComboPooledDataSource;

import edu.khai.applicationtracker.dao.AppUserDAO;
import edu.khai.applicationtracker.dao.AppUserUserRoleDAO;
import edu.khai.applicationtracker.dao.UserRoleDAO;
import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.model.AppUserUserRole;
import edu.khai.applicationtracker.model.UserRole;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context/dao-context.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class DbUnitExportUtilTest {
	final static Logger logger = Logger.getLogger(DbUnitExportUtilTest.class);

	private static final String DATASET_PATH = "src/test/resources/dbunit/AppUserDataSet.xml";

	private IDatabaseTester databaseTester = null;
	private IDataSet dataSet = null;

	@Autowired
	ComboPooledDataSource dataSource;

	@Autowired
	AppUserDAO appUserDAO;

	@Before
	public void setUp() throws Exception {
		this.dataSet = readDataSet(DATASET_PATH);
		this.databaseTester = setUpDatabaseTester(this.dataSource, this.dataSet);
		this.databaseTester.onSetup();
	}

	@After
	public void tearDown() throws Exception {
		this.databaseTester.onTearDown();
	}


	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(false)
	public void testGetAppUserByName() throws Exception {
		//get data from the dataset to check against
		Long appUserId = Long.valueOf((String)this.dataSet.getTable("appUser").getValue(0, "appUserId"));
		String username = (String)this.dataSet.getTable("appUser").getValue(0, "username");

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
		Long appUserId = Long.valueOf((String)this.dataSet.getTable("appUser").getValue(0, "appUserId"));
		String username = (String)this.dataSet.getTable("appUser").getValue(0, "username");

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


	private IDataSet readDataSet(String fileName) throws Exception {
		return new FlatXmlDataSetBuilder().build(new File(fileName));
	}

	private IDatabaseTester setUpDatabaseTester(DataSource dataSource, IDataSet dataSet) throws Exception{

		IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource) {
			//Neat hack. Adds new properties to the connection being returned ny the database tester
			@Override
			public IDatabaseConnection getConnection() throws Exception {
				IDatabaseConnection connection = super.getConnection();
				//apply additional properties to the connection
				connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
				return connection;
			}
		};

		//Set up before and after test behaviour
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		//Applying dataset
		databaseTester.setDataSet(dataSet);

		return databaseTester;
	}

}
