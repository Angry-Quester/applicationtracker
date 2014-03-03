package edu.khai.applicationtracker.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.model.AppUserUserRole;

public class UserDetailsProvider {

	public User getUserDetails(AppUser appUser) {
		String username = appUser.getUsername();
		String password = appUser.getPassword();

			Set<GrantedAuthority> grantedAuthority= new HashSet<GrantedAuthority>(0);

				Iterator<AppUserUserRole> i = appUser.getAppUserUserRoles().iterator();
				while(i.hasNext()) {
					SimpleGrantedAuthority sga =
							new SimpleGrantedAuthority(i.next().getUserRole().getAuthority());
						grantedAuthority.add(sga);
				}

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
