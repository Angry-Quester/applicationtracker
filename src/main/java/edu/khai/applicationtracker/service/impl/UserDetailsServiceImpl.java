package edu.khai.applicationtracker.service.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import edu.khai.applicationtracker.dao.AppUserDAO;
import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.model.AppUserPrincipal;
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

	/**
	 * Code made transactional in case something goes wrong when
	 * the application tries to get valid users.
	 * This code closely connected with DAO wich in it's turn, implements complex HQL request,
	 * wich fetches all elements of the "AppUser" object, including it's roles.
	 * Possible source of problems.
	 * Maybe not thogh.
	 *
	 * Now method returns my own implementation of the
	 * UserDetails object
	 *
	 * AppUserPrincipal extends User
	 * User implements  UserDetails
	 *
	 * @author	  Quester
	 * @version	 0.0
	 * @since	   2014-02-27
	 */
	@Override
	@Transactional
	public AppUserPrincipal loadUserByUsername(String userName)
														throws UsernameNotFoundException,
																DataAccessException {

		AppUser appUser = appUserDAO.getAppUserByNameWithRoles(userName);

			if (appUser == null) {
				throw new UsernameNotFoundException("user not found. UserName=["+userName+"]");
			}

		return userDetailsProvider.getUserDetails(appUser);
	}

}