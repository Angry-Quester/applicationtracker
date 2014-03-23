package edu.khai.applicationtracker.dao;

import org.apache.log4j.Logger;

//import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import edu.khai.applicationtracker.service.ApplicationService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:application-servlet.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class TestDAOTest {
	final static Logger logger = Logger.getLogger(TestDAOTest.class);

	@Autowired
	private ApplicationContext applicationContext;

	UserDetailsService uds = null;
	ApplicationService as = null;

	@Before
	public void setUp() throws Exception {
		 uds = (UserDetailsService)applicationContext.getBean("userDetailService");
		 as = (ApplicationService)applicationContext.getBean("applicationService");
	}

	@After
	public void tearDown() throws Exception {
		uds = null;
	}

	@Test
	@Ignore
//	@Transactional(propagation = Propagation.REQUIRED)
//	@Rollback(false)
	public void testTest() throws Exception {
		UserDetails ud = uds.loadUserByUsername("myname@mailbox.net");
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!! = "+ud);
	}

	@Test
//	@Transactional(propagation = Propagation.REQUIRED)
//	@Rollback(false)
	@Ignore
	public void testAs() throws Exception {
		boolean securityMarker =
				as.securityCheck(Long.valueOf(1), Long.valueOf(1));
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!! = "+securityMarker);
	}

}
