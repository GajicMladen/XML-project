package com.example.userservice.dto;

public class ErrorDTO {
	private String message;

	public ErrorDTO() {}
	
	public ErrorDTO(String msg) {
		this.message = msg;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
