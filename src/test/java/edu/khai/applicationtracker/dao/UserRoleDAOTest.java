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

import edu.khai.applicationtracker.model.UserRole;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:application-servlet.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class UserRoleDAOTest {
	final static Logger logger = Logger.getLogger(UserRoleDAOTest.class);

	@Autowired
	private ApplicationContext applicationContext;

	private UserRole userRole = null;
	private UserRoleDAO userRoleDAO= null;

	private static final String USER_ROLE = "ROLE_USER";

	@Before
	public void setUp() throws Exception {
		userRole = new UserRole();
		userRole.setAuthority(USER_ROLE);

		userRoleDAO = (UserRoleDAO)applicationContext.getBean("userRoleDAO");
	}

	@After
	public void tearDown() throws Exception {
		userRoleDAO = null;
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(false)
	public void testSaveUserRole() throws Exception {
		userRoleDAO.saveUserRole(userRole);

		assertNotNull("primary key assigned", userRole.getUserRoleId());
			logger.info(userRole);
		assertNotNull(userRole.getAuthority());
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(false)
	public void testAddAndRemoveUserRole() throws Exception {
		userRoleDAO.saveUserRole(userRole);

		assertNotNull("Checking User's Role ID value="+userRole.getUserRoleId(), userRole.getUserRoleId());
			logger.info(userRole);
		assertEquals(userRole.getAuthority(), USER_ROLE);

		if (logger.isDebugEnabled()) {
			logger.debug("removing UserRole...");
		}

		userRoleDAO.removeUserRole(userRole.getUserRoleId());

		assertNull(userRoleDAO.getUserRole(userRole.getUserRoleId()));
	}

}
