package com.bug.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

	@GetMapping("/user/login")
	public String userLogin() {
		return "UserLoginForm";
	}
	
	@GetMapping("/admin/login")
	public String adminLogin() {
		return "AdminLoginForm";
	}
	
	@GetMapping("/logoutSuccess")
	public String adinLogout() {
		return "logout";
	}
	
	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "denied";
	}
	
	@GetMapping("/user/register")
	public String register() {
		return "register";
	}
	
	
}
