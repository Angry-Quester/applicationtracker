package edu.khai.applicationtracker.dao;

import org.apache.log4j.Logger;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.util.UserDetailsProvider;
import edu.khai.applicationtracker.dao.AppUserDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:application-servlet.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class TestDAOTest {
	final static Logger logger = Logger.getLogger(TestDAOTest.class);

	@Autowired
	private ApplicationContext applicationContext;

	UserDetailsService uds = null;

	@Before
	public void setUp() throws Exception {
		 uds = (UserDetailsService)applicationContext.getBean("userDetailService");
	}

	@After
	public void tearDown() throws Exception {
		uds = null;
	}

	@Test
//	@Transactional(propagation = Propagation.REQUIRED)
//	@Rollback(true)
	public void testTest() throws Exception {
		UserDetails ud = uds.loadUserByUsername("myname@mailbox.net");
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!! = "+ud);
	}

}
