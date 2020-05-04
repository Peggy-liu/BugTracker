package com.bug.spring.model;

public class Response {
	private String status;
	private boolean data;
	
public Response(String status, boolean data) {
		super();
		this.status = status;
		this.data = data;
	}
public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(boolean data) {
		this.data = data;
	}


}
