package com.bug.spring.model;



public enum Status {
	OPEN("O"),CLOSE("C");
	private String code;
	private Status(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	
	public static Status fromCodeToStatus(String code) {
		switch(code) {
		case "O":
			return Status.OPEN;
		
		case"C":
			return Status.CLOSE;
		default:
			throw new IllegalArgumentException("the code is not supported!");
		}
	}
	
};
