package com.bug.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bug.spring.security.UserRepository;
import com.bug.spring.security.model.Role;
import com.bug.spring.security.model.User;

@Service
public class UserService {

	@Autowired
	UserRepository repo;

	public void addUser(User user) {
		repo.saveAndFlush(user);
	}

	public Optional<User> findByUsername(String username) {
		return repo.findByUsername(username);
	}

	public List<User> getAllUsers() {
		List<User> temp;
		List<User> result = new ArrayList<User>();
		temp = repo.findAll();
		for (User user : temp) {

			if (user.getRole() == Role.ROLE_USER) {
				result.add(user);
			}
		}

		return result;
	}
}
