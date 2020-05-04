package com.bug.spring.security;

import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder extends BCryptPasswordEncoder {

	public PasswordEncoder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PasswordEncoder(int strength, SecureRandom random) {
		super(strength, random);
		// TODO Auto-generated constructor stub
	}

	public PasswordEncoder(int strength) {
		super(strength);
		// TODO Auto-generated constructor stub
	}

}
