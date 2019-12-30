package com.code2.onlinebankingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
	@GetMapping("/login")
	public String showLogin(@RequestParam(value = "error", required=false)boolean error,Model model) {
		if(error) {
			String errorMessage="Invalid username or password!";
			model.addAttribute("errorMessage", errorMessage);
		}
		return "auth/login";
	}
	@GetMapping("/logout")
	public String showLogout(Model model) {
		String message="You have successfully logged out.";
		model.addAttribute("message", message);
		return "auth/login";
	}
	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "auth/access-denied";
	}
}
