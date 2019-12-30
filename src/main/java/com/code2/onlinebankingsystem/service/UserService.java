package com.code2.onlinebankingsystem.service;

import java.util.List;

import com.code2.onlinebankingsystem.entity.User;

public interface UserService {
	public void saveUser(User user);
	public List<User> getUsers();
	public User getUserById(Long id);
	public void updateUser(User user);
	public User getUserByUsername(String name);
}
