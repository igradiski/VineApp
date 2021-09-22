package com.hr.igz.VineApp.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hr.igz.VineApp.domain.User;
import com.hr.igz.VineApp.repository.UserRepository;

public class UserDetailsSecurityImpl implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Autowired
	UserRepository userRepository;
	

	private Long id;

	private String username;

	private String email;

	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsSecurityImpl(Long id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsSecurityImpl build(User user) {
		List<GrantedAuthority> authorities = user.getUserRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole().getRoleName()))
				.collect(Collectors.toList());

		return new UserDetailsSecurityImpl(
				user.getId(), 
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(), 
				authorities);
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsSecurityImpl user = (UserDetailsSecurityImpl) o;
		return Objects.equals(id, user.id);
	}
}
