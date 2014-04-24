package edu.khai.applicationtracker.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.springframework.security.core.context.SecurityContextHolder;

import testsetup.TestSecuritySetup;
import static org.junit.Assert.*;
import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.model.Application;
import edu.khai.applicationtracker.dao.AppUserDAO;
import edu.khai.applicationtracker.dao.ApplicationDAO;
import edu.khai.applicationtracker.service.impl.ApplicationServiceImpl;

/*Mockito imports*/
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

public class ApplicationServiceUnitTest {
	final static Logger logger = Logger.getLogger(ApplicationServiceUnitTest.class);

	private Application application;
	private ApplicationDAO mockApplicationDAO;
	private AppUserDAO mockAppUserDAO;
	private ApplicationServiceImpl applicationManagerImpl;

	@Before
	public void setUp() throws Exception {
		//Делаем экземпляр UserManager'a
		applicationManagerImpl = new ApplicationServiceImpl();

		//Делаем mock объект для ApplicationDAO
		mockApplicationDAO = mock(ApplicationDAO.class);
		//Делаем mock объект для ApplicationDAO
		mockAppUserDAO = mock(AppUserDAO.class);

		//Устанавливаем зависимости, которые раньше делал Spring
		applicationManagerImpl.setApplicationDAO(mockApplicationDAO);
		//Устанавливаем зависимости, которые раньше делал Spring
		applicationManagerImpl.setAppUserDAO(mockAppUserDAO);

		//Creating some random application for the test
		application = new Application();
		application.setGivenName("John");
		application.setFamilyName("Layman");
		application.setMiddleName("Doe");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				application.setBirthDate(sdf.parse("12-10-1960"));
				application.setCreationDate(sdf.parse("02-01-2014"));
				application.setLastModificationDate(sdf.parse("13-01-2014"));

		/* Place fake securityContext into static SecurityContextHolder*/
		SecurityContextHolder.setContext(TestSecuritySetup.buildMockSecurityContext());
	}

	@After
	public void tearDown() throws Exception {
		applicationManagerImpl = null;
		mockApplicationDAO = null;
		application = null;
	}

	@Test
	public void testGetApplicationsByAppUserId() throws Exception {
		//small set up
		List<Application> la = new ArrayList<Application>();
		la.add(application);
		//remember user id to check against
		Long userId = Long.valueOf(14);

		//given
		willReturn(la).given(mockApplicationDAO).getApplicationsByAppUserId(Long.valueOf(anyLong()));

		//when
		List<Application> applications = applicationManagerImpl.getApplicationsByAppUserId(userId);

		//then
		verify(mockApplicationDAO, times(1)).getApplicationsByAppUserId(userId);
		assertEquals("Expected size of the Applications list is wrong!!! :: ",
				la.size(),
				applications.size());

		logger.info("Found APPLICATIONS :: " + applications);
	}

	@Test
	public void testGetApplication() throws Exception {
		Long applicationId = Long.valueOf(33);
		//given
		willReturn(application).given(mockApplicationDAO).find(anyLong());
		//when
		Application found = applicationManagerImpl.getApplication(applicationId);
		//then
		verify(mockApplicationDAO, times(1)).find(applicationId);
		assertEquals("Found application is Wrong!!! ::",
						application.getFamilyName(),
						found.getFamilyName());
		logger.info("Found Application data :: " + found);
	}

	@Test
	public void testAddApplication() throws Exception {
		AppUser fakeAppUser = TestSecuritySetup.getFakeAppUserFromPrincipal();

		//given
		willReturn(fakeAppUser).given(mockAppUserDAO).find(anyLong());
		willDoNothing().given(mockApplicationDAO).add(application);
		//when
		Application found = applicationManagerImpl.addApplication(application);
		//then
		verify(mockApplicationDAO, times(1)).add(application);
		verifyNoMoreInteractions(mockApplicationDAO);

		verify(mockAppUserDAO, times(1)).find(anyLong());
		verifyNoMoreInteractions(mockAppUserDAO);

		assertEquals("Found application is Wrong!!! ::",
						application.getFamilyName(),
						found.getFamilyName());
		logger.info("Added Application data :: " + found);
	}

	@Test
	public void testUpdateApplication() throws Exception {
		//Long applicationId = Long.valueOf(33);
		//given
		willDoNothing().given(mockApplicationDAO).update(application);
		//when
		Application found = applicationManagerImpl.updateApplication(application);
		//then
		verify(mockApplicationDAO, times(1)).update(application);
		verifyNoMoreInteractions(mockApplicationDAO);

		assertEquals("Updated application is Wrong!!! ::",
						application.getFamilyName(),
						found.getFamilyName());
		logger.info("Added Application data :: " + found);
	}

	@Test
	public void testRemoveApplication() throws Exception {

		Long applicationId = Long.valueOf(33);

		AppUser fakeAppUser = TestSecuritySetup.getFakeAppUserFromPrincipal();
		//given
		willReturn(fakeAppUser).given(mockAppUserDAO).find(anyLong());
		willReturn(application).given(mockApplicationDAO).find(anyLong());
		willDoNothing().given(mockApplicationDAO).removeById(applicationId);
		//when
		applicationManagerImpl.removeApplication(applicationId);
		//then
		verify(mockApplicationDAO, times(1)).find(anyLong());
		verify(mockApplicationDAO, times(1)).remove(application);
		verifyNoMoreInteractions(mockApplicationDAO);

		verify(mockAppUserDAO, times(1)).find(anyLong());
		verifyNoMoreInteractions(mockAppUserDAO);
	}

}