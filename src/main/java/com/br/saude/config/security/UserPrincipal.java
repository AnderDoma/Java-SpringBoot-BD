package com.br.saude.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.saude.entity.User;

public class UserPrincipal implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	
	public UserPrincipal(User user) {
		this.userName = user.getUsername();
		this.password = user.getPassword();
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		// ROLE_ADMIN, ROLE_USER, ADMIN, USER ....
		user.getRoles().stream().map(role -> {
			return new SimpleGrantedAuthority("ROLE_".concat(role.getName()));
		}).collect(Collectors.toList());
		
		this.authorities = authorities;
	}
	
	public static UserPrincipal create(User user) {
		return new UserPrincipal(user);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
