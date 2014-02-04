package edu.khai.applicationtracker.service.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.khai.applicationtracker.dao.AppUserDAO;
import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.util.UserDetailsProvider;

public class UserDetailsServiceImpl implements UserDetailsService {

	private AppUserDAO appUserDAO;

	private UserDetailsProvider userDetailsProvider;
	
	public void setAppUserDAO(AppUserDAO appUserDAO) {
		this.appUserDAO = appUserDAO;
	}
	
	public void setUserDetailsProvider(UserDetailsProvider userDetailsProvider) {
		this.userDetailsProvider= userDetailsProvider;
	}	

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException,
    													DataAccessException {
		// TODO Auto-generated method stub
		AppUser appUser = appUserDAO.getAppUserByName(userName);
			if (appUser == null) {
				throw new UsernameNotFoundException("user ot found");
			}
		return userDetailsProvider.getUserDetails(appUser);
	}

}
