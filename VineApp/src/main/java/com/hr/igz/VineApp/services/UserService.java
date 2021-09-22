package com.hr.igz.VineApp.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hr.igz.VineApp.domain.User;

public interface UserService {


	User loadUserByUsername(String username) throws UsernameNotFoundException;

}
