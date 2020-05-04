package com.bug.spring.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bug.spring.model.Status;
import com.bug.spring.model.Ticket;

public interface TicketRepository extends CrudRepository<Ticket,Integer> {
	public Ticket[] findByCreator(String username);
	
	@Modifying
	@Query("UPDATE Ticket c SET c.title = :title, c.language=:language, c.description=:description WHERE c.ID=:ID")
	@Transactional
	void updateTicket(@Param("ID")int ID, @Param("title")String title, @Param("language")String language,
			           @Param("description")String description);
	
	@Modifying
	@Query("UPDATE Ticket c SET c.status = :status WHERE c.ID=:ID")
	@Transactional
	void closeTicket(@Param("ID")int ID, @Param("status") Status status);
	
}
