package edu.khai.applicationtracker.dao;

import java.io.File;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;

public class DBUnitSetup {
	final static Logger logger = Logger.getLogger(DBUnitSetup.class);

	private IDatabaseTester databaseTester = null;
	private IDataSet dataSet = null;


	public IDataSet getDataSet() {
		return this.dataSet;
	}

	public IDatabaseTester getDatabaseTester() {
		return this.databaseTester;
	}


	public IDataSet readDataSet(String fileName) throws Exception {
		this.dataSet = new FlatXmlDataSetBuilder().build(new File(fileName));
		return this.dataSet;
	}


	public IDatabaseTester setUpDatabaseTester(DataSource dataSource, IDataSet dataSet) throws Exception{
		this.databaseTester = new DataSourceDatabaseTester(dataSource) {
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
