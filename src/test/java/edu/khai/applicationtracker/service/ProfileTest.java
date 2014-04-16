package edu.khai.applicationtracker.service;


import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsInstanceOf.*;
/*Mockito imports*/
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-context/test-context.xml"})
@ActiveProfiles({"test1"})
public class ProfileTest {
	final static Logger logger = Logger.getLogger(ProfileTest.class);

	@Autowired
	String profileString;

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void loadUserByUsername() throws Exception {
		System.out.printf("%n%n%s%n%n", profileString);
	}
}