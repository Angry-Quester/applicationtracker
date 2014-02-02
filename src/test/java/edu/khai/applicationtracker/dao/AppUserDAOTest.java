package edu.khai.applicationtracker.dao;

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
import edu.khai.applicationtracker.dao.AppUserDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:application-servlet.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class AppUserDAOTest {
	final static Logger logger = Logger.getLogger(AppUserDAOTest.class);

	@Autowired
	private ApplicationContext applicationContext;

	private AppUser appUser = null;
    private AppUserDAO appUserDAO = null;

    @Before
    public void setUp() throws Exception {
        appUser = new AppUser();
        appUser.setName("myname@mailbox.net");
        appUser.setPassword("password");        
        appUser.setAuthorities("ROLE_ADMIN");
        appUser.setDisabled(false);
        appUser.setLocked(false);
    	
        appUserDAO = (AppUserDAO)applicationContext.getBean("appUserDAO");
    }

    @After
    public void tearDown() throws Exception {
        appUserDAO = null;
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    @Rollback(true)
    public void testSaveUser() throws Exception {
        appUserDAO.saveAppUser(appUser);

        assertNotNull("primary key assigned", appUser.getAppUserId());
            logger.info(appUser);
        assertNotNull(appUser.getPassword());
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    @Rollback(true)
    public void testAddAndRemoveUser() throws Exception {
    	appUserDAO.saveAppUser(appUser);

        assertNotNull("Checking appUser's ID value="+appUser.getAppUserId(),appUser.getAppUserId());
            logger.info(appUser);
        assertEquals(appUser.getPassword(), "password");

        if (logger.isDebugEnabled()) {
            logger.debug("removing appUser...");
        }

        appUserDAO.removeAppUser(appUser.getAppUserId());

        assertNull(appUserDAO.getAppUser(appUser.getAppUserId()));
    }
    
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    @Rollback(true)
    public void testGetUserByName() throws Exception {
        appUserDAO.saveAppUser(appUser);
        
        AppUser testAppUser = appUserDAO.getAppUserByName("myname@mailbox.net");
        
        assertNotNull(testAppUser);
        assertEquals(testAppUser.getName(), appUser.getName());
    }    

}
