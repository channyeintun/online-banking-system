package com.code2.onlinebankingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.code2.onlinebankingsystem.entity.Role;
import com.code2.onlinebankingsystem.entity.User;
import com.code2.onlinebankingsystem.repository.RoleRepository;
import com.code2.onlinebankingsystem.repository.UserRepository;

@Service
public class UserSeviceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public void saveUser(User user) {
		Role userRole=roleRepository.getOne(1);
		user.addRole(userRole);
		String password=user.getPassword();
		String encodedPassword=passwordEncoder.encode(password);
		user.setPassword(encodedPassword);
		
		userRepository.save(user);
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		
		return userRepository.getOne(id);

	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

	@Override
	public User getUserByUsername(String name) {
		return userRepository.getUserByUsername(name);
	}

}
