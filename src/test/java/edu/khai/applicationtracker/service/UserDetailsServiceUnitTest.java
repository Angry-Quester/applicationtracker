package edu.khai.applicationtracker.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsInstanceOf.*;
import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.model.AppUserPrincipal;
import edu.khai.applicationtracker.dao.AppUserDAO;
import edu.khai.applicationtracker.service.impl.UserDetailsServiceImpl;
import edu.khai.applicationtracker.util.UserDetailsProvider;

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
		//Create a real service object
		userDetailsServiceImpl = new UserDetailsServiceImpl();

		//create mock dependencies
		mockAppUserDAO = mock(AppUserDAO.class);
		mockUserDetailsProvider = mock(UserDetailsProvider.class);

		//Inject dependencies
		userDetailsServiceImpl.setAppUserDAO(mockAppUserDAO);
		userDetailsServiceImpl.setUserDetailsProvider(mockUserDetailsProvider);
	}

	@After
	public void tearDown() throws Exception {
		//clear all references
		userDetailsServiceImpl = null;
		mockAppUserDAO = null;
		mockUserDetailsProvider = null;
	}

	@Test
	public void loadUserByUsername() throws Exception {
		//create fake Appuser
		AppUser appUser = new AppUser();
		appUser.setId(Long.valueOf(1));
		appUser.setAppUserId(Long.valueOf(1));
		appUser.setUsername("somename@mailbox.ru");
		appUser.setPassword("password");
		appUser.setAccountNonExpired(true);
		appUser.setAccountNonLocked(true);
		appUser.setCredentialsNonExpired(true);
		appUser.setEnabled(true);

		//remember his name
		String username = appUser.getUsername();

		//create granted authorities for the AppUserPrincipal
		List<GrantedAuthority> grantedAutorities = new ArrayList<GrantedAuthority>();
			grantedAutorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		//create fake AppUserPrincipal
		AppUserPrincipal appUserPrincipal = new AppUserPrincipal(
			appUser.getId(),
			appUser.getUsername(),
			appUser.getPassword(),
			appUser.isAccountNonExpired(),
			appUser.isAccountNonLocked(),
			appUser.isCredentialsNonExpired(),
			appUser.isEnabled(),
			grantedAutorities);

		//given
		willReturn(appUser).given(mockAppUserDAO).getAppUserByNameWithRoles(username);
		willReturn(appUserPrincipal).given(mockUserDetailsProvider).getUserDetails(appUser);

		//when
		UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

		//then
		verify(mockAppUserDAO, times(1)).getAppUserByNameWithRoles(username);
		verify(mockUserDetailsProvider, times(1)).getUserDetails(appUser);

		assertNotNull("User details are empty!!! :: ", userDetails);
		assertEquals("Wrong user details!!! ::",
						username,
						userDetails.getUsername());
		assertThat("userDetailsServiceImpl returned wrong instance!!! ::",
						userDetails,
						instanceOf(AppUserPrincipal.class));

		logger.info("\n USER DETAILS RETURNED FROM THE METHOD :: " + userDetails + "\n");
	}

}