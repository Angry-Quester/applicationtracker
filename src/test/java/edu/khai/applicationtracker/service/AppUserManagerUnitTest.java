package edu.khai.applicationtracker.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;
import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.dao.AppUserDAO;
import edu.khai.applicationtracker.service.impl.AppUserServiceImpl;

/*Mockito imports*/
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

public class AppUserManagerUnitTest {
	final static Logger logger = Logger.getLogger(AppUserManagerUnitTest.class);
/*
	private AppUserDAO mockAppUserDAO;
	private AppUserServiceImpl appUserManagerImpl;


	@Before
	public void setUp() throws Exception {
		//Делаем экземпляр AppUserManager'a
		appUserManagerImpl = new AppUserServiceImpl();
		//Делаем mock объект для ApplicationDAO
		mockAppUserDAO = mock(AppUserDAO.class);
		//Устанавливаем зависимости, которые раньше делал Spring
		appUserManagerImpl.setAppUserDAO(mockAppUserDAO);
	}

	@After
	public void tearDown() throws Exception {
		appUserManagerImpl = null;
		mockAppUserDAO = null;
	}

	@Test
	public void testGetAppUsers() throws Exception {
		List<AppUser> appUsers = new ArrayList<AppUser>();
		appUsers.add(new AppUser());

		//given
		willReturn(appUsers).given(mockAppUserDAO).getAppUsers();
		//when
		List<AppUser> testAppusers = appUserManagerImpl.getAppUsers();
		//then
		verify(mockAppUserDAO, times(1)).getAppUsers();
		assertEquals(appUsers, testAppusers);
	}

	@Test
	public void testGetAppUser() throws Exception {
		AppUser appUser = new AppUser();
			appUser.setUsername("myname@mailbox.net");
		//given
		willReturn(appUser).given(mockAppUserDAO).getAppUser(anyLong());
		//when
		AppUser testAppuser = appUserManagerImpl.getAppUser(new Long(123));
		//then
		verify(mockAppUserDAO, times(1)).getAppUser(new Long(123));
		assertEquals(appUser.getUsername(), testAppuser.getUsername());
	}

	@Test
	public void testSaveAppUser() throws Exception {
		AppUser appUser = new AppUser();
			appUser.setUsername("myname@mailbox.net");
		//given
		willDoNothing().given(mockAppUserDAO).saveAppUser(appUser);
		//when
		AppUser testAppuser = appUserManagerImpl.saveAppUser(appUser);
		//then
		verify(mockAppUserDAO, times(1)).saveAppUser(appUser);
		assertEquals(appUser.getUsername(), testAppuser.getUsername());
	}

	@Test
	public void testRemoveAppUser() throws Exception {
		AppUser appUser = new AppUser();
			appUser.setUsername("myname@mailbox.net");
		//given
		willDoNothing().given(mockAppUserDAO).removeAppUser(anyLong());
		//when
		appUserManagerImpl.removeAppUser(anyLong());
		//then
		verify(mockAppUserDAO, times(1)).removeAppUser(anyLong());
	}
*/
}