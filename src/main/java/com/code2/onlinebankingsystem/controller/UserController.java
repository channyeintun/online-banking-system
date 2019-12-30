package com.code2.onlinebankingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.code2.onlinebankingsystem.entity.User;
import com.code2.onlinebankingsystem.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/new")
	public String showCreateForm(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		return "admin/new-user";
	}
	
	@GetMapping("/detail")
	public String showDetail(@RequestParam("id")Long id,Model model) {
		
		User user = userService.getUserById(id);
		model.addAttribute("user",user);
		return "admin/user-detail";
	}
	
	@GetMapping
	public String showUserList(Model model) {
		List<User> userList = userService.getUsers();
		model.addAttribute("users",userList);
		return "admin/user-list";
	}
	
	@PostMapping
	public String createUser(@ModelAttribute("user")User user) {
		
		userService.saveUser(user);
		
		return "redirect:/admin/users";
	}
	@PostMapping("/update")
	public String updateUser(@RequestParam("newPassword")String newPassword,@ModelAttribute("user")User user) {
		
		if(!newPassword.isEmpty()) {
			String encodedPassword=passwordEncoder.encode(newPassword);
			user.setPassword(encodedPassword);
		}
		User newUser=userService.getUserById(user.getId());
		user.setRoles(newUser.getRoles());
		userService.updateUser(user);
		
		return "redirect:/admin/users";
	}
	@GetMapping("update")
	public String showUpdateUser(@RequestParam("userId")Long id,Model model) {
		User user=userService.getUserById(id);
		model.addAttribute("user", user);
		return "admin/edit-user";
	}
	
}
