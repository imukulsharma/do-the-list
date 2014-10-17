package com.list.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.list.app.model.Role;
import com.list.app.model.User;

@Service("userDetailsService")
public class CustomUserService implements UserDetailsService {

	@Autowired
	private UserService userService;

	// API

	public CustomUserService() {

	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final User foundByUsername = userService.getByUsername(username);
		System.out.println("User Present : " + foundByUsername.getUsername());

		System.out.println("Fetching User Details : " + username);
		final Set<Role> authorities = foundByUsername.getUserRole();
		System.out.println("Roles Size : " + authorities.size());

		List<String> listRoles = new ArrayList<String>();
		for (Role myRole : authorities) {
			System.out.println(myRole.getRole());
			listRoles.add(myRole.getRole());
		}

		final List<GrantedAuthority> authoritiesForSpring = AuthorityUtils.createAuthorityList(listRoles
				.toArray(new String[listRoles.size()]));

		return new org.springframework.security.core.userdetails.User(username, foundByUsername.getPassword(),
				authoritiesForSpring);
	}

}
