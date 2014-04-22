package edu.khai.applicationtracker.controller;

import java.util.Arrays;

import org.apache.log4j.Logger;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;

import edu.khai.applicationtracker.model.AppUserPrincipal;

import static org.mockito.Mockito.*;


/**
 * This class helps to test method where i explicitly use
 * SecurityContextHolder
 * to get a principal from my Spring Security context
 *
 * @author Quester
 *
 */
public class TestSecuritySetup {
	final static Logger logger = Logger.getLogger(TestSecuritySetup.class);


	/**
	 * This code helps to test method where i explicitly use SecurityContextHolder
	 * to get a principal from my Spring Security context
	 *
	 * Build SecurityContext object with the User inside it
	 *
	 *
	 * User parameters are:
	 * Long    userId = 1L
	 * String  username = name@mail.net
	 * String  password = pass
	 * boolean enabled = true
	 * boolean accountNonExpired = true
	 * boolean credentialsNonExpired = true
	 * boolean accountNonLocked = true
	 * Collection<? extends GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"))
	 *
	 * @return mocked SecurityContext
	 */
	public static SecurityContext buildMockSecurityContext(){
		Authentication authentication = mock(Authentication.class);
		SecurityContext securityContext = mock(SecurityContext.class);

		/* This is fake AppUserPrincipal to use inside the test*/
		AppUserPrincipal aup = new AppUserPrincipal(1l,
													"name@mail.net",
													"pass",
													true,
													true,
													true,
													true,
													Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));

		/* Mock calls to the security infrastructure */
		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(securityContext.getAuthentication().getPrincipal()).thenReturn(aup);

		return securityContext;
	}

}