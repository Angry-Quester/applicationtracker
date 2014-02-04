package edu.khai.applicationtracker.service;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.springframework.security.core.userdetails.User;

import static org.junit.Assert.*;
import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.util.UserDetailsProvider;

public class UserDetailsProviderUnitTest {
	final static Logger logger = Logger.getLogger(UserDetailsProviderUnitTest.class);

	private UserDetailsProvider userDetailsProvider;
	
	@Before
	public void setUp() throws Exception {
		//Делаем экземпляр userDetailsServiceImpl. Это как раз тот класс который нужно тестировать
		userDetailsProvider = new UserDetailsProvider();
			//тут не будет никаких mock объектов
		//и зависимостей тоже нет
	}

	@After
	public void tearDown() throws Exception {
		userDetailsProvider = null;
	}

	@Test
	public void testloadUserByUsername() throws Exception {
		String username = "somename@mailbox.ru";
		String password = "password";
		String authorities = "ROLE_USER";
		
		
		AppUser appUser = new AppUser();
			appUser.setUsername(username);
			appUser.setPassword(password);
			appUser.setAuthorities(authorities);

		//given
		
		//when
		User user = userDetailsProvider.getUserDetails(appUser); 
		//then
		assertNotNull(user);
		assertEquals(user.getUsername(), appUser.getUsername());
	}

}