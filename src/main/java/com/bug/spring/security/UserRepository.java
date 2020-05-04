package com.bug.spring.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bug.spring.security.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public Optional<User> findByUsername(String username);
	
}
