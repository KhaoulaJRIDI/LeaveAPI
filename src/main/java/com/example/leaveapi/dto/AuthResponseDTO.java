package com.example.leaveapi.dto;

public class AuthResponseDTO {
	
	private String token;

	public AuthResponseDTO(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}
