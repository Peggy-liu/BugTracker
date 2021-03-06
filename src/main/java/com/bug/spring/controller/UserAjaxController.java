package com.bug.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bug.spring.model.Response;
import com.bug.spring.model.Ticket;
import com.bug.spring.service.TicketService;
import com.bug.spring.service.UserService;

@RestController
public class UserAjaxController {

	
	@Autowired
	UserService userService;
	
	
	@Autowired
	TicketService ticketService;
	
	@GetMapping("/user/existed")
	public Response nameCheck(@RequestParam("username")String username) {
		Response result= new Response("Done", checkUsernameExist(username));
		return result;
	}
	
	
	@PutMapping("/ticket")
	public Response update(@RequestBody Ticket ticket) {

	ticketService.updateTicket(ticket);
	Response result = new Response("Done", true);
		return result;
	}
	
	@PutMapping("/ticket/{id}")
	public Response closeTicket(@PathVariable("id") String ID) {
		ticketService.closeTicket(ID);
		Response result = new Response("Done", true);
		return result;
	}
	
	protected boolean checkUsernameExist(String username) {
		boolean result;
		if(userService.findByUsername(username).isPresent()) {
			result=true;
		}
		else {
			result=false;
		}
		return result;
	}
}
