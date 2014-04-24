package edu.khai.applicationtracker.service;

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

public class AppUserServiceUnitTest {
	final static Logger logger = Logger.getLogger(AppUserServiceUnitTest.class);

	private AppUserDAO mockAppUserDAO;

	private AppUserServiceImpl appUserServiceImpl;

	private AppUser appUser;


	@Before
	public void setUp() throws Exception {
		//Делаем экземпляр AppUserManager'a
		appUserServiceImpl = new AppUserServiceImpl();
		//Делаем mock объект для ApplicationDAO
		mockAppUserDAO = mock(AppUserDAO.class);

		//Устанавливаем зависимости, которые раньше делал Spring
		appUserServiceImpl.setAppUserDAO(mockAppUserDAO);

		//create fake Appuser
		appUser = new AppUser();
		appUser.setId(Long.valueOf(1));
		appUser.setAppUserId(Long.valueOf(1));
		appUser.setUsername("somename@mailbox.ru");
		appUser.setPassword("password");
		appUser.setAccountNonExpired(true);
		appUser.setAccountNonLocked(true);
		appUser.setCredentialsNonExpired(true);
		appUser.setEnabled(true);
	}

	@After
	public void tearDown() throws Exception {
		mockAppUserDAO = null;
		appUserServiceImpl = null;
	}

	@Test
	public void testGetAppUserByName() throws Exception {
		String username = appUser.getUsername();
		//given
		willReturn(appUser).given(mockAppUserDAO).getAppUserByName(anyString());
		//when
		AppUser found = appUserServiceImpl.getAppUserByName(username);
		//then
		verify(mockAppUserDAO, times(1)).getAppUserByName(username);

		assertEquals("Found appUser is Wrong!!! ::",
				appUser.getUsername(),
				found.getUsername());

	}

	@Test
	public void testGetAppUserByNameWithRoles() throws Exception {
		String username = appUser.getUsername();
		//given
		willReturn(appUser).given(mockAppUserDAO).getAppUserByNameWithRoles(anyString());
		//when
		AppUser found = appUserServiceImpl.getAppUserByNameWithRoles(username);
		//then
		verify(mockAppUserDAO, times(1)).getAppUserByNameWithRoles(username);

		assertEquals("Found appUser is Wrong!!! ::",
				appUser.getUsername(),
				found.getUsername());
	}

}