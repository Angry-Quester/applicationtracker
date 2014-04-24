package edu.khai.applicationtracker.dao;

import org.apache.log4j.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context/dao-context.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class ContextDAOTest{
	final static Logger logger = Logger.getLogger(ContextDAOTest.class);

	@Before
	public void setUp() throws Exception {

	}


	@After
	public void tearDown() throws Exception {

	}


	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	@Rollback(true)
	public void testContext() throws Exception{
		System.out.println("Starting DAO context. And nothing else.");
	}

}