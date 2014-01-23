package edu.khai.applicationtracker.dao;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.khai.applicationtracker.model.Application;
import edu.khai.applicationtracker.dao.ApplicationDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class ApplicationDAOTest {
	final static Logger logger = Logger.getLogger(ApplicationDAOTest.class);

	@Autowired
	private ApplicationContext applicationContext;

	private Application application = null;
    private ApplicationDAO applicationDAO = null;

    @Before
    public void setUp() throws Exception {
        applicationDAO = (ApplicationDAO)applicationContext.getBean("applicationDAO");
    }

    @After
    public void tearDown() throws Exception {
        applicationDAO = null;
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    @Rollback(false)
    public void testSaveUser() throws Exception {
        application = new Application();
        application.setApplicationType("app-type");
        application.setApplicationData(1);

        applicationDAO.saveApplication(application);

        assertNotNull("primary key assigned", application.getApplicationId());
            logger.info(application);
        assertNotNull(application.getApplicationType());
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    @Rollback(false)
    public void testAddAndRemoveUser() throws Exception {
        application = new Application();
        application.setApplicationType("app-type");
        application.setApplicationData(1);

        applicationDAO.saveApplication(application);

        assertNotNull("Checking application's ID value="+application.getApplicationId(),application.getApplicationId());
            logger.info(application);
        assertEquals(application.getApplicationType(), "app-type");

        if (logger.isDebugEnabled()) {
            logger.debug("removing application...");
        }

        applicationDAO.removeApplication(application.getApplicationId());

        assertNull(applicationDAO.getApplication(application.getApplicationId()));
    }

}
