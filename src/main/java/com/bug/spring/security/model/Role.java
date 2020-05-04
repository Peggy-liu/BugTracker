package com.bug.spring.security.model;

public enum Role {
	ROLE_USER("U"), ROLE_ADMIN("A");

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private String code;

	Role(String code) {
		this.code = code;
	}

	public static Role FromCodeToRole(String code) {
		switch(code) {
			case "U":
				return ROLE_USER;
			
			case "A":
				return ROLE_ADMIN;
			default:
				throw new IllegalArgumentException("the code is not supported!");
		}
		
	}

}
