package edu.khai.applicationtracker.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.model.AppUserPrincipal;
import edu.khai.applicationtracker.model.AppUserUserRole;

public class UserDetailsProvider {

	public AppUserPrincipal getUserDetails(AppUser appUser) {
		Long userId = appUser.getAppUserId();
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

		AppUserPrincipal appUserPrincipal
					= new AppUserPrincipal(
								userId,
								username,
								password,
								enabled,
								accountNonExpired,
								credentialsNonExpired,
								accountNonLocked,
								grantedAuthority);

		return appUserPrincipal;
	}

}
