package testsetup;

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


/**
 * Utility helper class. Implements basic DBUNit functionality and several helper methods.
 *
 * @author Random Screen
 *
 */
public class DBUnitSetup {
    final static Logger logger = Logger.getLogger(DBUnitSetup.class);

    //new JdbcDatabaseTester(DRIVER_CLASS, CONNECTION_URL, USER, PASSWORD) {
    /*
    public static final String DRIVER_CLASS= "com.mysql.jdbc.Driver";
    public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/applicationTrackerDbTest?useUnicode=true&characterEncoding=UTF-8";
    public static final String USER = "atroot";
    public static final String PASSWORD = "ghbdtnatroot";
     */
    private static IDatabaseTester databaseTester = null;
    private static IDataSet dataSet = null;


    /**
     * Read dataset from a flat xml file
     *
     * @param fileName The name of the file (full path actually) storing flat dataset information for the test
     * @return DBUnit IDataSet for the given file
     * @throws general Exception. I'm lazy to implement exception logic at this point
     */
    public static IDataSet buildDataSet(String fileName) throws Exception {
        dataSet = new FlatXmlDataSetBuilder().build(new File(fileName));
        return dataSet;
    }


    /**
     * Set up working IDatabaseTester for the current test Suite
     *
     * @param dataSource data source to connect to DB. For now i just use data sources provided by Spring injections
     * @param dataSet
     * @return
     * @throws Exception
     */
    public static IDatabaseTester buildUpDatabaseTester(DataSource dataSource, IDataSet dataSet) throws Exception{

        databaseTester =
                new DataSourceDatabaseTester(dataSource) {
            //Neat hack. Adds new properties to the connection being returned by the database tester
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

    /**
     * Full cleanup operation used inside JUnit "AfterClass" method
     *
     * @param datasetPath  The name of the file (full path actually) storing flat dataset information for the test
     * @throws Exception
     */
    public static void afterTestCleanUp(String datasetPath) throws Exception{
            databaseTester.setDataSet(dataSet);
            databaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
            databaseTester.onTearDown();
            databaseTester.setTearDownOperation(DatabaseOperation.NONE);
    }


    /**
     * Return some test data directly from the confugured db unit dataset
     *
     * @param tableName
     * @param row Row for the table in the current test data set
     * @param columnName Column name in the row
     * @return String representation of the test data
     * @throws Exception
     */
    public static String getTestData(String tableName, int row, String columnName) throws Exception{
        return (String)dataSet.getTable(tableName).getValue(row, columnName);
    }

    /**
     * Return row count for the given table directly from the confugured db unit dataset
     *
     * @param tableName Name of the DB table
     * @return row count for the given table
     * @throws Exception
     */
    public static int getRowCount(String tableName) throws Exception{
        return dataSet.getTable(tableName).getRowCount();
    }

}