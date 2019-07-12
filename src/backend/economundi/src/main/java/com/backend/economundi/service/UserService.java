package com.backend.economundi.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.backend.economundi.shared.UserDto;

public interface UserService extends UserDetailsService{
	
	UserDto createUser(UserDto user);

}
