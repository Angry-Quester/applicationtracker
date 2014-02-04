package edu.khai.applicationtracker.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import edu.khai.applicationtracker.model.AppUser;

public class UserDetailsProvider {

	public User getUserDetails(AppUser appUser) {
		String username = appUser.getUsername();
		String password = appUser.getPassword();
			Set<GrantedAuthority> grantedAuthority= new HashSet<GrantedAuthority>();
			grantedAuthority.add(new SimpleGrantedAuthority(appUser.getAuthorities()));

		boolean accountNonExpired = appUser.isAccountNonExpired();
		boolean accountNonLocked = appUser.isAccountNonLocked();
		boolean credentialsNonExpired = appUser.isCredentialsNonExpired();
		boolean enabled = appUser.isEnabled();
		
		User user = new User(username, 
								password, 
								enabled, 
								accountNonExpired, 
								credentialsNonExpired, 
								accountNonLocked, 
								grantedAuthority);

		return user;
	}

}
