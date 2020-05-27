package com.bug.spring.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bug.spring.model.Status;
import com.bug.spring.model.Ticket;
import com.bug.spring.security.PasswordEncoder;
import com.bug.spring.security.model.MyUserDetails;
import com.bug.spring.security.model.Role;
import com.bug.spring.security.model.User;
import com.bug.spring.service.TicketService;
import com.bug.spring.service.UserService;

@Controller
public class UserController {

	
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	UserService userService;
	

	@Autowired
	PasswordEncoder encoder;
	

	
	@RequestMapping("/user/home")
	public String oauthToHome(Authentication authToken, Model model) {
		//form login
		if(authToken instanceof OAuth2AuthenticationToken) {
			System.out.println(authToken.getAuthorities());
			OAuth2User user = (OAuth2User) authToken.getPrincipal();
			model.addAttribute("username", user.getAttributes().get("name"));
			model.addAttribute("attributes", user.getAttributes());
		}
		else {
			MyUserDetails details = (MyUserDetails) authToken.getPrincipal();
			model.addAttribute("username", details.getUsername());
		}
		
		return "user_home";
		
	} 
	
	
	@PostMapping("/user/register")
	public String register(User user , Model model) {

		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(Role.ROLE_USER);
		
		userService.addUser(user);
        model.addAttribute("user", user);
        
		
		return "register_success";
	}
	
	@RequestMapping("/user/create")
	public String redirectToAdd() {
		return "add";
	}
	
	@PostMapping("/user/transfer")
	public String createNewTicket(Authentication authentication, Ticket ticket,Model model) {
		ticket.setCreator(authentication.getName());
		ticket.setStatus(Status.OPEN);
		
			ticketService.createTicket(ticket);
		
			return "success_add";

		
	}
	
	@GetMapping("/user/view")
	public String getAll(Authentication auth, Model model) {
		Ticket[] results = null;
		
			results = ticketService.getAllTickets(auth.getName());
			model.addAttribute("tickets",results);
			System.out.println();
			return "viewAll";
		

	}
	
	@GetMapping("/user/edit")
	public String redirectToUpdate(@RequestParam(name="id")int ID, Model model) {
		
		Optional<Ticket> ticket = ticketService.getTicket(ID);
		ticket.orElseThrow(()->new IllegalArgumentException());
		model.addAttribute(ticket.get());
		return "update";
	}
	
	
}
