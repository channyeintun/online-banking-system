package com.code2.onlinebankingsystem.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.code2.onlinebankingsystem.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.code2.onlinebankingsystem.entity.User foundUser = userRepository.getUserByUsername(username);
		if (foundUser != null) {
			return foundUser;
		}
		throw new UsernameNotFoundException("Username not found");
	}

}
