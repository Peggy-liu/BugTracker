package com.bug.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bug.spring.dao.TicketRepository;
import com.bug.spring.model.Status;
import com.bug.spring.model.Ticket;

@Service
public class TicketService {

	@Autowired
	TicketRepository repo;
	
	public void createTicket(Ticket ticket) {
		repo.save(ticket);
	}
	
	public Ticket[] getAllTickets(String username) {
		return repo.findByCreator(username);
	}
	
	public Optional<Ticket> getTicket(int ID) {
		return repo.findById(ID);
	}
	
	public void updateTicket(Ticket ticket) {
		repo.updateTicket(ticket.getID(), ticket.getTitle(), ticket.getLanguage(), ticket.getDescription());
	}
	
	public void closeTicket(String ID) {
		repo.closeTicket(Integer.valueOf(ID), Status.CLOSE);
	}
}
