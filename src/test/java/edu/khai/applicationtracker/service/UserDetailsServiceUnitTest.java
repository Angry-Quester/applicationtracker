package edu.khai.applicationtracker.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.Assert.*;
import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.dao.AppUserDAO;
import edu.khai.applicationtracker.service.impl.UserDetailsServiceImpl;
import edu.khai.applicationtracker.util.UserDetailsProvider;
//import static org.mockito.Matchers.anyString;
/*Mockito imports*/
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

public class UserDetailsServiceUnitTest {
	final static Logger logger = Logger.getLogger(UserDetailsServiceUnitTest.class);

	private UserDetailsServiceImpl userDetailsServiceImpl;

	private AppUserDAO mockAppUserDAO;

	private UserDetailsProvider mockUserDetailsProvider;

	@Before
	public void setUp() throws Exception {
		//Делаем экземпляр userDetailsServiceImpl. Это как раз тот класс который нужно тестировать
		userDetailsServiceImpl = new UserDetailsServiceImpl();
			//Делаем mock объект для AppUserDAO
			mockAppUserDAO = mock(AppUserDAO.class);
			//Делаем mock объект для UserDetailsProvider
			mockUserDetailsProvider = mock(UserDetailsProvider.class);
		//Устанавливаем зависимости, которые раньше делал Spring
		userDetailsServiceImpl.setAppUserDAO(mockAppUserDAO);
		userDetailsServiceImpl.setUserDetailsProvider(mockUserDetailsProvider);
	}

	@After
	public void tearDown() throws Exception {
		userDetailsServiceImpl = null;
		mockAppUserDAO = null;
		mockUserDetailsProvider = null;
	}

	@Test
	public void loadUserByUsername() throws Exception {
		String username = "somename@mailbox.ru";
		String password = "password";
		List<GrantedAuthority> grantedAutorities = new ArrayList<GrantedAuthority>();
			grantedAutorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		AppUser appUser = new AppUser();
			appUser.setUsername("somename@mailbox.ru");
		User user = new User(username, password, grantedAutorities);

		//given
		willReturn(appUser).given(mockAppUserDAO).getAppUserByNameWithRoles("somename@mailbox.ru");
		willReturn(user).given(mockUserDetailsProvider).getUserDetails(appUser);
		//when
		UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername("somename@mailbox.ru");
		//then
		verify(mockAppUserDAO, times(1)).getAppUserByNameWithRoles("somename@mailbox.ru");
		verify(mockUserDetailsProvider, times(1)).getUserDetails(appUser);
		assertNotNull(userDetails);
		assertEquals(username, userDetails.getUsername());
		logger.info(userDetails);
	}

}